package com.spothero.challenge.usecase

import com.spothero.challenge.common.Resource
import com.spothero.challenge.domain.model.Spot
import com.spothero.challenge.domain.repository.SpotHeroRepositoryInterface
import com.spothero.challenge.mappers.toSpots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject


class GetSpotsUseCase @Inject constructor(
    private val spotsRepo: SpotHeroRepositoryInterface
) {
    operator fun invoke(): Flow<Resource<List<Spot>>> = flow {
        try {
            emit(Resource.Loading())

            val spots = spotsRepo.getSpotDTOs()
            if(spots == null) {
                emit(Resource.Error("API error - Spots list not found."))
                return@flow
            }

            emit(Resource.Success(spots.toSpots()))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage
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
