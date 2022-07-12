package com.hashconcepts.buycart.data.mapper

import com.hashconcepts.buycart.data.remote.dto.request.ProfileDto
import com.hashconcepts.buycart.data.remote.dto.response.ProductsDto
import com.hashconcepts.buycart.domain.model.Product
import com.hashconcepts.buycart.domain.model.UserProfile

/**
 * @created 12/07/2022 - 4:59 AM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

fun ProfileDto.toUserProfile(): UserProfile {
    return UserProfile(
        email = email,
        password = password,
        username = username,
        name = name,
        address = address,
        phone = phone
    )
}

fun ProductsDto.toProduct(): Product {
    return Product(
        id = id,
        title = title,
        image = image,
        price = price
    )
}