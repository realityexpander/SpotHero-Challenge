package com.spothero.challenge.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Spot(
    val id: Int,
    val address: Address,
    val description: String,
    val distance: String,
    @SerialName("facility_photo") var facilityPhoto: String,
    val price: Long
)