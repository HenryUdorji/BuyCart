package com.hashconcepts.buycart.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hashconcepts.buycart.ui.theme.secondaryColor

/**
 * @created 11/07/2022 - 7:43 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Composable
fun CircularProgress(
    modifier: Modifier = Modifier,
    color: Color = secondaryColor,
    strokeWidth: Dp = 1.dp
) {
    CircularProgressIndicator(
        strokeWidth = strokeWidth,
        modifier = modifier.size(15.dp),
        color = color
    )
}