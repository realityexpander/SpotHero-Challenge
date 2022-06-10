package com.spothero.challenge.domain.repository

import com.spothero.challenge.data.model.SpotDTO

interface SpotHeroRepositoryInterface {

    suspend fun getSpotDTOs(): List<SpotDTO>

    suspend fun getSpotDTOById(spotId: Int): SpotDTO?

}