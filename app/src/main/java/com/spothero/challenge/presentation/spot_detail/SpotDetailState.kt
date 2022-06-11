package com.spothero.challenge.presentation.spot_list

import com.spothero.challenge.domain.model.Spot


data class SpotDetailState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val spot: Spot? = null
)
