package com.spothero.challenge.usecase

import com.spothero.challenge.common.Resource
import com.spothero.challenge.domain.model.Spot
import com.spothero.challenge.domain.repository.SpotHeroRepositoryInterface
import com.spothero.challenge.mappers.toSpot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class GetSpotInfoUseCase @Inject constructor(
    private val spotsRepo: SpotHeroRepositoryInterface
) {
    operator fun invoke(spotId: Int): Flow<Resource<Spot>> = flow {
        try {
            emit(Resource.Loading())

            val spot = spotsRepo.getSpotDTOById(spotId)
            if(spot == null) {
                emit(Resource.Error("Spot not found."))
                return@flow
            }
            emit(Resource.Success(spot.toSpot()))
        } catch (e: IOException) {
            emit(
                Resource.Error(e.localizedMessage
                ?: "Could not reach server, check internet connection.")
            )
        } catch (e: Exception) {
            emit(
                Resource.Error(e.localizedMessage
                    ?: "Unknown Error")
            )
        }
    }
}
