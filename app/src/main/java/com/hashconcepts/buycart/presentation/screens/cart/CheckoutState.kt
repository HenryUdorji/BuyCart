package com.hashconcepts.buycart.presentation.screens.cart

data class CheckoutState(
    val items: String = "1",
    val price: String = "$1",
    val charges: String = "$30.00",
    val grandTotal: String = "$1"
)