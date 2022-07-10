package com.hashconcepts.buycart.presentation.screens.auth.register

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hashconcepts.buycart.presentation.components.ConnectivityStatus
import com.hashconcepts.buycart.presentation.components.CustomTextField
import com.hashconcepts.buycart.presentation.screens.auth.AuthScreenEvents
import com.hashconcepts.buycart.presentation.screens.auth.AuthViewModel
import com.hashconcepts.buycart.presentation.screens.destinations.HomeScreenDestination
import com.hashconcepts.buycart.presentation.screens.destinations.LoginScreenDestination
import com.hashconcepts.buycart.ui.theme.backgroundColor
import com.hashconcepts.buycart.ui.theme.disableColor
import com.hashconcepts.buycart.ui.theme.errorColor
import com.hashconcepts.buycart.ui.theme.secondaryColor
import com.hashconcepts.buycart.utils.UIEvents
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

/**
 * @created 29/06/2022 - 8:57 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Destination
@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegisterScreen(
    navigator: DestinationsNavigator,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(backgroundColor)
        systemUiController.setNavigationBarColor(backgroundColor)
    }

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.authChannelFlow.collectLatest { uiEvents ->
            when (uiEvents) {
                is UIEvents.ErrorEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        uiEvents.message,
                        duration = SnackbarDuration.Short
                    )
                }
                is UIEvents.SuccessEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        "Registration successful",
                        duration = SnackbarDuration.Short
                    )
                    navigator.popBackStack()
                    navigator.navigate(LoginScreenDestination)

//                                val openDialog = remember { mutableStateOf(true) }
//                                if (openDialog.value) {
//                                    CustomAlertDialog(onDismissRequest = {
//                                        openDialog.value = false
//                                        navigator.popBackStack()
//                                        navigator.navigate(LoginScreenDestination)
//                                    })
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        content = {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor)
            ) {
                if (viewModel.loadingState) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .height(2.dp)
                            .fillMaxWidth(),
                        color = errorColor
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Register",
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(30.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                        .background(Color.White)
                        .weight(1f)
                ) {
                    val keyboardController = LocalSoftwareKeyboardController.current

                    var username by remember { mutableStateOf("") }
                    var password by remember { mutableStateOf("") }
                    var email by remember { mutableStateOf("") }
                    var phoneNo by remember { mutableStateOf("") }

                    CustomTextField(
                        label = "Username",
                        text = username,
                        placeholder = "Enter Username",
                        onValueChange = { username = it }) {
                        keyboardController?.hide()
                    }

                    CustomTextField(
                        label = "Password",
                        text = password,
                        placeholder = "Enter Password",
                        onValueChange = { password = it }) {
                        keyboardController?.hide()
                    }

                    CustomTextField(
                        label = "Email",
                        text = email,
                        placeholder = "Enter Email",
                        keyboardType = KeyboardType.Email,
                        onValueChange = { email = it }) {
                        keyboardController?.hide()
                    }

                    CustomTextField(
                        label = "Phone No",
                        text = phoneNo,
                        placeholder = "Enter Phone No",
                        keyboardType = KeyboardType.Phone,
                        onValueChange = { phoneNo = it }) {
                        keyboardController?.hide()
                    }

                    Spacer(modifier = Modifier.height(100.dp))

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(horizontal = 20.dp),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            viewModel.onAuthAction(
                                AuthScreenEvents.RegisterClicked(
                                    username,
                                    password,
                                    email,
                                    phoneNo
                                )
                            )
                        }) {
                        Text(text = "Register", style = MaterialTheme.typography.button)
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Already have an account? ",
                            style = MaterialTheme.typography.body1,
                            color = disableColor
                        )
                        Text(
                            text = "Login",
                            style = MaterialTheme.typography.body1,
                            color = secondaryColor,
                            modifier = Modifier.clickable {
                                navigator.popBackStack()
                                navigator.navigate(LoginScreenDestination)
                            }
                        )
                    }
                }

                //ConnectivityStatus()
            }
        }
    )
}