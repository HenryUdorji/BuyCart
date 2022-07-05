package com.hashconcepts.buycart.presentation.screens.auth.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.hashconcepts.buycart.R
import com.hashconcepts.buycart.ui.theme.BuyCartTheme
import com.hashconcepts.buycart.ui.theme.primaryColor

/**
 * @created 05/07/2022 - 2:04 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Composable
fun CustomAlertDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        CustomDialogUI(onDismissRequest)
    }
}

@Composable
fun CustomDialogUI(onDismissRequest: () -> Unit) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,
        backgroundColor = Color.White,
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.ic_successful),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(100.dp)
                    .padding(top = 35.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Registration Successful",
                style = MaterialTheme.typography.h2,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                TextButton(onClick = { onDismissRequest() }) {
                    Text(
                        text = "Close",
                        style = MaterialTheme.typography.button,
                        color = primaryColor
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CustomDialogPreview() {
    BuyCartTheme() {
        CustomDialogUI(onDismissRequest = {})
    }
}