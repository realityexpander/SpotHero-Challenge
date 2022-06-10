package com.spothero.challenge.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AddressDTO(
    val street: String,
    val city: String,
    val state: String
)