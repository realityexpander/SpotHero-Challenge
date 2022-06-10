package com.spothero.challenge.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpotDTO(
    val id: Int,
    val address: AddressDTO,
    val description: String,
    val distance: String,
    @SerializedName("facility_photo")
    @SerialName("facility_photo")
    var facilityPhoto: String = "",
    val price: Long
)