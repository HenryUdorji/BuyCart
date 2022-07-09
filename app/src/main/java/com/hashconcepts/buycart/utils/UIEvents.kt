package com.hashconcepts.buycart.utils

/**
 * @created 09/07/2022 - 2:31 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

sealed class UIEvents {
    object SuccessEvent: UIEvents()
    data class ErrorEvent(val message: String): UIEvents()
}