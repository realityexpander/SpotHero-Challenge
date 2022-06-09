package com.spothero.challenge.data

import android.content.Context
import com.spothero.challenge.data.model.Spot
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.reactivex.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * API used to retrieve SpotHero spots.
 * Uses Ktor with OkHttp
 *
 * @param context Context for MockClient
 * @see HttpClient
 */
class SpotHeroApi(context: Context) {
    companion object {
        private val BASE_URL = "http://api.spothero.com/v1"
        private val PHOTO_LOCATION = "//android_asset/"
    }

    private val client = HttpClient(OkHttp) {
        engine {
            addInterceptor(MockClient(context))
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }

    }

    /**
     * Retrieve all available spots.
     * Suspended function for use with coroutines.
     *
     * @return List of Spots
     * @see Spot
     */
    suspend fun getSpots(): List<Spot> = client.get<List<Spot>> {
        endpoint("/spots")
    }.map { spot ->
        spot.apply {
            facilityPhoto = PHOTO_LOCATION + facilityPhoto
        }
    }

    /**
     * Retrieve all available spots.
     *
     * @return Rx Single that emits list of Spots
     * @see Spot
     */
    fun getSpotsObservable() = Single.create<List<Spot>> { emitter ->
        GlobalScope.launch {
            emitter.onSuccess(getSpots())
        }
    }

    /**
     * Retrieve one Spot by ID.
     * Suspended function for use with coroutines.
     *
     * @param spotId ID of spot to retrieve
     * @return Spot with matching ID. Null if no spot found.
     * @see Spot
     */
    suspend fun getSpotById(spotId: Int): Spot? = getSpots().firstOrNull {
        it.id == spotId
    }

    /**
     * Retrieve one Spot by ID.
     *
     * @param spotId ID of spot to retrieve
     * @return Rx Single that emits Spot with matching ID. Emits error if no matching spot is found.
     * @see Spot
     */
    fun getSpotByIdObservable(spotId: Int) = Single.create<Spot> { emitter ->
        GlobalScope.launch {
            val spot = getSpotById(spotId)
            if (spot != null) {
                emitter.onSuccess(spot)
                return@launch
            }
            emitter.onError(Throwable("No spot found matching ID $spotId"))
        }
    }

    private fun HttpRequestBuilder.endpoint(endpoint: String) = this.url(BASE_URL + endpoint)
}