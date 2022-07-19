package com.hashconcepts.buycart.data.remote.dto.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @created 28/06/2022 - 3:51 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
data class ProfileDto(
    val username: String,
    val password: String,
    val email: String,
    val phone: String,
    val name: Name = Name(),
    val address: Address = Address()
)

@Parcelize
data class Name(
    val firstname: String = "John",
    val lastname: String = "Doe",
): Parcelable

@Parcelize
data class Address(
    val city: String = "kilcoole",
    val street: String = "7835 new road",
    val number: Int = 3,
    val zipcode: String = "12926-3874",
    val geolocation: Geolocation = Geolocation(),
): Parcelable

@Parcelize
data class Geolocation(
    val lat: String = "-37.3159",
    val long: String = "81.1496"
): Parcelable
