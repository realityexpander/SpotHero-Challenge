package com.spothero.challenge.data.repository

import com.spothero.challenge.data.model.SpotDTO
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * API used to retrieve SpotHero spots.
 * Uses Retrofit with OkHttp
 *
 * @param context Context for MockClient
 * @see HttpClient
 */
interface SpotHeroApi2 {
    /**
     * Retrieve all available spots.
     * Suspended function for use with coroutines.
     *
     * @return List of Spots
     * @see Spot
     */
    @GET("v1/spots")
    suspend fun getSpotDTOs(): List<SpotDTO>

    /**
     * Retrieve one Spot by ID.
     * Suspended function for use with coroutines.
     *
     * @param spotId ID of spot to retrieve
     * @return Spot with matching ID. Null if no spot found.
     * @see Spot
     */
    @GET("v1/spots/{spotId}")
    suspend fun getSpotDTOById(@Path("spotId") spotId: Int): SpotDTO
}