package com.spothero.challenge.di

import android.content.Context
import com.spothero.challenge.BuildConfig
import com.spothero.challenge.common.Constants
import com.spothero.challenge.data.remote.MockInterceptor
import com.spothero.challenge.data.repository.SpotHeroApi
import com.spothero.challenge.data.repository.SpotHeroRepositoryImpl
import com.spothero.challenge.domain.repository.SpotHeroRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named("MockInterceptor")
    fun provideMockInterceptor(@ApplicationContext context: Context): Interceptor = MockInterceptor(context)

    @Provides
    @Singleton
    @Named("MockOkHttpClient")
    fun provideMockOkHttpClient(
        @Named("MockInterceptor") mockInterceptor: Interceptor
    ): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(mockInterceptor)
        .build()

    @Provides
    @Singleton
    @Named("MockSpotHeroApi")
    fun provideMockSpotHeroApi(
        @Named("MockOkHttpClient") client: OkHttpClient
    ): SpotHeroApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpotHeroApi::class.java)
    }

    @Provides
    @Singleton
    @Named("SpotHeroApi")
    fun provideSpotHeroApi(): SpotHeroApi {  // uses OkHttpClient by default
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpotHeroApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSpotHeroRepository(
        @Named("SpotHeroApi") spotHeroApi: SpotHeroApi,
        @Named("MockSpotHeroApi") mockSpotHeroApi: SpotHeroApi
    ): SpotHeroRepositoryInterface {

        if (BuildConfig.MOCK_MODE) {
            return SpotHeroRepositoryImpl(mockSpotHeroApi)
        }

        return SpotHeroRepositoryImpl(spotHeroApi)
    }
}