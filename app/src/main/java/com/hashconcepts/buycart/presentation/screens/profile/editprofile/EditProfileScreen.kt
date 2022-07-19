package com.hashconcepts.buycart.presentation.screens.profile.editprofile

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hashconcepts.buycart.data.remote.dto.request.Name
import com.hashconcepts.buycart.domain.model.PaymentInfo
import com.hashconcepts.buycart.domain.model.UserProfile
import com.hashconcepts.buycart.presentation.components.CustomTextField
import com.hashconcepts.buycart.presentation.components.CustomToolbar
import com.hashconcepts.buycart.presentation.components.DateVisualTransformation
import com.hashconcepts.buycart.presentation.screens.profile.PaymentInfoEvents
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
fun EditProfileScreen(
    navigator: DestinationsNavigator,
    userProfile: UserProfile,
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
            CustomToolbar(title = "Edit Profile") { navigator.navigateUp() }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(Color.White)
            ) {
                FormContentSection(
                    userProfile = userProfile,
                    profileViewModel = profileViewModel
                )
            }
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FormContentSection(
    userProfile: UserProfile,
    profileViewModel: ProfileViewModel
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var email by remember { mutableStateOf(userProfile.email) }
    var username by remember { mutableStateOf(userProfile.username) }
    var phone by remember { mutableStateOf(userProfile.phone) }
    var firstName by remember { mutableStateOf(userProfile.name.firstname) }
    var lastName by remember { mutableStateOf(userProfile.name.lastname) }


    CustomTextField(
        label = "Email",
        text = email,
        placeholder = "Enter Email",
        keyboardType = KeyboardType.Email,
        onValueChange = { email = it }) {
        keyboardController?.hide()
    }

    CustomTextField(
        label = "Username",
        text = username,
        placeholder = "Enter Username",
        onValueChange = { username = it }) {
        keyboardController?.hide()
    }

    CustomTextField(
        label = "Phone Number",
        text = phone,
        placeholder = "Enter Phone Number",
        keyboardType = KeyboardType.Phone,
        onValueChange = { phone = it }) {
        keyboardController?.hide()
    }

    CustomTextField(
        label = "First Name",
        text = firstName,
        placeholder = "Enter First Name",
        onValueChange = { firstName = it }) {
        keyboardController?.hide()
    }

    CustomTextField(
        label = "Last Name",
        text = lastName,
        placeholder = "Enter Last Name",
        onValueChange = { lastName = it }) {
        keyboardController?.hide()
    }

    Spacer(modifier = Modifier.height(50.dp))

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(10.dp),
        onClick = {}
    ) {
        Text(text = "Update Profile", style = MaterialTheme.typography.button)
    }
}
