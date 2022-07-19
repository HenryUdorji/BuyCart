package com.hashconcepts.buycart.presentation.screens.profile.address

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hashconcepts.buycart.data.remote.dto.request.Address
import com.hashconcepts.buycart.domain.model.UserProfile
import com.hashconcepts.buycart.presentation.components.CustomTextField
import com.hashconcepts.buycart.presentation.components.CustomToolbar
import com.hashconcepts.buycart.presentation.screens.profile.ProfileViewModel
import com.hashconcepts.buycart.ui.theme.backgroundColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * @created 19/07/2022 - 12:09 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Destination
@Composable
fun AddressScreen(
    navigator: DestinationsNavigator,
    address: Address,
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
            CustomToolbar(title = "Address") { navigator.navigateUp() }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(Color.White)
            ) {
                FormContentSection(
                    address = address,
                    profileViewModel = profileViewModel
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FormContentSection(
    address: Address,
    profileViewModel: ProfileViewModel
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var city by remember { mutableStateOf(address.city) }
    var street by remember { mutableStateOf(address.street) }
    var zipcode by remember { mutableStateOf(address.zipcode) }
    var houseNo by remember { mutableStateOf(address.number) }
    var geolocation by remember { mutableStateOf(address.geolocation) }


    CustomTextField(
        label = "City",
        text = city,
        placeholder = "Enter City",
        keyboardType = KeyboardType.Email,
        onValueChange = { city = it }) {
        keyboardController?.hide()
    }

    CustomTextField(
        label = "Street",
        text = street,
        placeholder = "Enter Street",
        onValueChange = { street = it }) {
        keyboardController?.hide()
    }

    CustomTextField(
        label = "ZipCode",
        text = zipcode,
        placeholder = "Enter ZipCode",
        keyboardType = KeyboardType.Phone,
        onValueChange = { zipcode = it }) {
        keyboardController?.hide()
    }

//    CustomTextField(
//        label = "First Name",
//        text = firstName,
//        placeholder = "Enter First Name",
//        onValueChange = { firstName = it }) {
//        keyboardController?.hide()
//    }
//
//    CustomTextField(
//        label = "Last Name",
//        text = lastName,
//        placeholder = "Enter Last Name",
//        onValueChange = { lastName = it }) {
//        keyboardController?.hide()
//    }

    Spacer(modifier = Modifier.height(50.dp))

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(10.dp),
        onClick = {}
    ) {
        Text(text = "Update Address", style = MaterialTheme.typography.button)
    }
}
