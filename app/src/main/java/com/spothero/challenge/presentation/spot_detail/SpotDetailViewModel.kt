package com.spothero.challenge.presentation.spot_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spothero.challenge.common.Constants.PARAM_SPOT_ID
import com.spothero.challenge.common.Resource
import com.spothero.challenge.domain.model.Spot
import com.spothero.challenge.presentation.spot_list.SpotDetailState
import com.spothero.challenge.usecase.GetSpotInfoUseCase
import com.spothero.challenge.usecase.GetSpotsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

// ViewModel is necessary to keep the state of the view between configuration changes

@HiltViewModel
class SpotDetailViewModel @Inject constructor(
    private val getSpotInfoUseCase: GetSpotInfoUseCase,
    savedStateHandle: SavedStateHandle  // gets nav params
) : ViewModel() {

    var state = mutableStateOf(SpotDetailState())
        private set

    init {
        savedStateHandle.get<Int>(PARAM_SPOT_ID)?.let { spotId ->
            getSpotInfo(spotId)
        } ?: run {
            state.value = state.value.copy(
                isLoading = false,
                isError = true,
                errorMessage = "No spotId parameter provided."
            )
        }

    }

    private fun getSpotInfo(spotId: Int) {

        if (spotId <= 0) {
            state.value = state.value.copy(
                isLoading = false,
                isError = true,
                errorMessage = "Invalid spotId parameter provided."
            )
            return
        }

        getSpotInfoUseCase(spotId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    state.value = state.value.copy(
                        isLoading = false,
                        isError = false,
                        spot = result.data ?: Spot()
                    )
                }
                is Resource.Error -> {
                    state.value = state.value.copy(
                        isLoading = false,
                        isError = true,
                        errorMessage = result.message ?: "Unknown Error"
                    )
                }
                is Resource.Loading -> {
                    state.value = state.value.copy(
                        isLoading = true,
                        isError = false
                    )
                }
            }
        }.launchIn(viewModelScope)

    }
}