package com.spothero.challenge.presentation.spot_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spothero.challenge.common.Resource
import com.spothero.challenge.data.repository.SpotHeroApi2
import com.spothero.challenge.usecase.GetSpotsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

// ViewModel is necessary to keep the state of the view between configuration changes

//@HiltViewModel
class SpotListViewModel @Inject constructor(
    private val getSpotsUseCase: GetSpotsUseCase, // this has the repository injected
    val api: SpotHeroApi2
): ViewModel() {

    var state = mutableStateOf(SpotListState())
        private set

    init {
        getSpots()
    }

    private fun getSpots() {

        getSpotsUseCase().onEach { result ->
//            val result2: Resource<*> = // keep for testing
//                Resource.Error<List<Spot>>(errorMessage = "", message = "Network error.")

                state.value = state.value.copy(
                    isLoading = false,
                    spots = result.data ?: emptyList()
                )

            when (result) {
                is Resource.Success -> {
                    state.value = state.value.copy(
                        isLoading = false,
                        isError = false,
                        spots = result.data ?: emptyList()
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