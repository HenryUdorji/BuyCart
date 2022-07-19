package com.hashconcepts.buycart.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hashconcepts.buycart.R

/**
 * @created 19/07/2022 - 4:18 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Composable
fun CustomToolbar(
    title: String,
    onNavigateUp: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        IconButton(onClick = onNavigateUp) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = null
            )
        }

        Text(
            text = title,
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .fillMaxWidth().weight(1f)
                .padding(top = 10.dp, bottom = 10.dp, end = 20.dp),
            textAlign = TextAlign.Center
        )
    }
}