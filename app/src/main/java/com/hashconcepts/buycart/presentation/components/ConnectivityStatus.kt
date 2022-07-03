package com.hashconcepts.buycart.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hashconcepts.buycart.R
import com.hashconcepts.buycart.utils.ConnectionState
import com.hashconcepts.buycart.utils.connectivityState
import kotlinx.coroutines.delay

/**
 * @created 20/06/2022 - 4:03 AM
 * @project ComposePokedex
 * @author  ifechukwu.udorji
 */

@Composable
fun ConnectivityStatusBox(
    isConnected: Boolean
) {
    val backgroundColor by animateColorAsState(targetValue = if (isConnected) Color.Green else Color.Red)
    val message = if (isConnected) "Back Online!" else "No Internet Connection!"
    val icon =
        if (isConnected) R.drawable.ic_connectivity_available else R.drawable.ic_connectivity_unavailable

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Connection icon",
                tint = Color.White
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = message,
                color = Color.White,
                fontSize = 15.sp,
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Composable
fun ConnectivityStatus() {
    val connection by connectivityState()
    val isConnected = connection == ConnectionState.Available
    var visibility by remember { mutableStateOf(false) }

    AnimatedVisibility(
        visible = visibility,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        ConnectivityStatusBox(isConnected = isConnected)
    }

    LaunchedEffect(isConnected) {
        visibility = if (!isConnected) {
            true
        } else {
            delay(2000)
            false
        }
    }
}