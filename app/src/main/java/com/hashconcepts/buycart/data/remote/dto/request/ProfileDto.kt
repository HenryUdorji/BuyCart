package com.hashconcepts.buycart.data.remote.dto.request

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

data class Name(
    val firstname: String = "John",
    val lastname: String = "Doe",
)

data class Address(
    val city: String = "kilcoole",
    val street: String = "7835 new road",
    val number: Int = 3,
    val zipcode: String = "12926-3874",
    val geolocation: Geolocation = Geolocation(),
)

data class Geolocation(
    val lat: String = "-37.3159",
    val long: String = "81.1496"
)
