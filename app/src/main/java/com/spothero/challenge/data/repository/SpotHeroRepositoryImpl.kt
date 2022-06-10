package com.spothero.challenge.data.repository

import com.spothero.challenge.data.model.SpotDTO
import com.spothero.challenge.domain.repository.SpotHeroRepositoryInterface
import javax.inject.Inject

class SpotHeroRepositoryImpl @Inject constructor(
    private val spotHeroApi: SpotHeroApi2
): SpotHeroRepositoryInterface {

    override suspend fun getSpotDTOs(): List<SpotDTO> {
        return spotHeroApi.getSpotDTOs()
    }

    override suspend fun getSpotDTOById(spotId: Int): SpotDTO {
        return spotHeroApi.getSpotDTOById(spotId)
    }
}