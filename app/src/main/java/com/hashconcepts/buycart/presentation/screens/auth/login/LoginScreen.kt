package com.hashconcepts.buycart.presentation.screens.auth.login

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.SystemUiController
import com.hashconcepts.buycart.presentation.components.ConnectivityStatus
import com.hashconcepts.buycart.presentation.components.CustomTextField
import com.hashconcepts.buycart.presentation.screens.auth.AuthViewModel
import com.hashconcepts.buycart.presentation.screens.auth.AuthScreenEvents
import com.hashconcepts.buycart.ui.theme.backgroundColor
import com.hashconcepts.buycart.ui.theme.disableColor
import com.hashconcepts.buycart.ui.theme.errorColor
import com.hashconcepts.buycart.ui.theme.secondaryColor

/**
 * @created 29/06/2022 - 8:57 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    systemUiController: SystemUiController,
    onRegisterClicked: () -> Unit,
    onLoginSuccessful: () -> Unit,
) {
    SideEffect {
        systemUiController.setStatusBarColor(backgroundColor)
        systemUiController.setNavigationBarColor(backgroundColor)
    }

    val viewModel = hiltViewModel<AuthViewModel>()
    val loginScreenState = viewModel.loginScreenState

    val scaffoldState = rememberScaffoldState()

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

                if (loginScreenState.isLoading) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .height(2.dp)
                            .fillMaxWidth(),
                        color = errorColor
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Login",
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

                    var username by remember { mutableStateOf("mor_2314") }
                    var password by remember { mutableStateOf("83r5^_") }

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

                    Spacer(modifier = Modifier.height(100.dp))

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(horizontal = 20.dp),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            viewModel.onAuthAction(
                                AuthScreenEvents.LoginClicked(
                                    username,
                                    password
                                )
                            )
                        }) {
                        Text(text = "Login", style = MaterialTheme.typography.button)
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Don't have an account? ",
                            style = MaterialTheme.typography.body1,
                            color = disableColor
                        )
                        Text(
                            text = "Register",
                            style = MaterialTheme.typography.body1,
                            color = secondaryColor,
                            modifier = Modifier.clickable {
                                onRegisterClicked()
                            }
                        )
                    }
                }

                ConnectivityStatus()

                if (loginScreenState.formError != null) {
                    LaunchedEffect(key1 = scaffoldState.snackbarHostState) {
                        scaffoldState.snackbarHostState.showSnackbar(
                            loginScreenState.formError,
                            duration = SnackbarDuration.Short
                        )
                    }
                }

                if (loginScreenState.error != null) {
                    LaunchedEffect(key1 = scaffoldState.snackbarHostState) {
                        scaffoldState.snackbarHostState.showSnackbar(
                            loginScreenState.error,
                            duration = SnackbarDuration.Short
                        )
                    }
                }

                if (loginScreenState.successful) {
                    onLoginSuccessful()
                }
            }
        })
}