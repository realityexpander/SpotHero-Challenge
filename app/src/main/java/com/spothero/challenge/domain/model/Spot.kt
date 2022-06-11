package com.spothero.challenge.domain.model

data class Spot(
    val id: Int = 0,
    val address: Address = Address(),
    val description: String = "Unknown description",
    val distance: String = " Unknown distance",
    var facilityPhoto: String = "",
    val price: Long = 0,
)
