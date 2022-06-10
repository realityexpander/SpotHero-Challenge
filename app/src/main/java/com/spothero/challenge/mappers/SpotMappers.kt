package com.spothero.challenge.mappers

import com.spothero.challenge.data.model.AddressDTO
import com.spothero.challenge.data.model.SpotDTO
import com.spothero.challenge.domain.model.Spot

fun SpotDTO.toSpot() = Spot(
    id = id,
    address = address.toAddress(),
    description = description,
    distance = distance,
    facilityPhoto = facilityPhoto,
    price = price,
)

fun Spot.toSpotDTO() = SpotDTO(
    id = id,
    address = address.toAddressDTO(),
    description = description,
    distance = distance,
    facilityPhoto = facilityPhoto,
    price = price
)

fun List<SpotDTO>.toSpots() = this.map{ it.toSpot() }