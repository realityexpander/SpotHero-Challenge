package com.spothero.challenge.domain.model

data class Spot(
    val id: Int,
    val address: Address,
    val description: String,
    val distance: String,
    var facilityPhoto: String,
    val price: Long
)
