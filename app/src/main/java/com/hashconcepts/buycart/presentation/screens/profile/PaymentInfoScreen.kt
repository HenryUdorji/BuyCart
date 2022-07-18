package com.hashconcepts.buycart.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hashconcepts.buycart.domain.model.UserProfile
import com.hashconcepts.buycart.presentation.components.CustomTextField
import com.hashconcepts.buycart.presentation.components.DateVisualTransformation
import com.hashconcepts.buycart.ui.theme.backgroundColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * @created 17/07/2022 - 5:23 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
@OptIn(ExperimentalComposeUiApi::class)
@Destination
@Composable
fun PaymentInfoScreen(
    navigator: DestinationsNavigator,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(backgroundColor)
        systemUiController.setNavigationBarColor(Color.White)
    }

    val scaffoldState = rememberScaffoldState()


    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = backgroundColor
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Credit/Debit Card",
                style = MaterialTheme.typography.h1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                textAlign = TextAlign.Center
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(Color.White)
            ) {
                val focusManager = LocalFocusManager.current
                val keyboardController = LocalSoftwareKeyboardController.current
                var cardNumber by remember { mutableStateOf("") }
                var cardName by remember { mutableStateOf("") }
                var cardExpiryDate by remember { mutableStateOf("") }
                var cardCVV by remember { mutableStateOf("") }

                val maxCVV = 3
                val maxCardNumber = 16


                CustomTextField(
                    label = "Card Number",
                    text = cardNumber,
                    placeholder = "Enter Card Number",
                    keyboardType = KeyboardType.Number,
                    onValueChange = {
                        cardNumber = it.take(maxCardNumber)
                        if (it.length > maxCardNumber) {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    }) {
                    keyboardController?.hide()
                }

                CustomTextField(
                    label = "CardHolder Name",
                    text = cardName,
                    placeholder = "Enter CardHolder Name",
                    onValueChange = { cardName = it }) {
                    keyboardController?.hide()
                }

                CustomTextField(
                    label = "Card Expiry",
                    text = cardExpiryDate,
                    placeholder = "Enter Card Expiry",
                    visualTransformation = DateVisualTransformation(),
                    keyboardType = KeyboardType.Number,
                    onValueChange = {
                        cardExpiryDate = it.take(maxCVV)
                        if (it.length > maxCardNumber) {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    }) {
                    keyboardController?.hide()
                }

                CustomTextField(
                    label = "Card CVV",
                    text = cardCVV,
                    placeholder = "Enter Card CVV",
                    keyboardType = KeyboardType.Number,
                    onValueChange = { cardCVV = it }) {
                    keyboardController?.hide()
                }

                Spacer(modifier = Modifier.height(50.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 20.dp),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {

                    }) {
                    Text(text = "Save Card", style = MaterialTheme.typography.button)
                }
            }
        }
    }

}