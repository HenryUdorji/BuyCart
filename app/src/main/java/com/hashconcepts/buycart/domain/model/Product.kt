package com.hashconcepts.buycart.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @created 12/07/2022 - 4:51 AM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Entity(tableName = "wishlist")
data class Product(
    @PrimaryKey
    val id: Int,
    val image: String,
    val price: String,
    val title: String,
    val quantity: Int
)
