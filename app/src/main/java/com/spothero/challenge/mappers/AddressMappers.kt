package com.spothero.challenge.mappers

import com.spothero.challenge.domain.model.Address
import com.spothero.challenge.data.model.AddressDTO

fun Address.toAddressDTO() = AddressDTO(
    street = street,
    city = city,
    state = state
)

fun AddressDTO.toAddress() = Address(
    street = street,
    city = city,
    state = state
)

fun List<AddressDTO>.toAddresses() = this.map{ it.toAddress() }