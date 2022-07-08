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
import com.hashconcepts.buycart.presentation.components.ConnectivityStatus
import com.hashconcepts.buycart.presentation.components.CustomTextField
import com.hashconcepts.buycart.presentation.screens.auth.AuthScreenEvents
import com.hashconcepts.buycart.presentation.screens.auth.AuthViewModel
import com.hashconcepts.buycart.ui.theme.backgroundColor
import com.hashconcepts.buycart.ui.theme.disableColor
import com.hashconcepts.buycart.ui.theme.errorColor
import com.hashconcepts.buycart.ui.theme.secondaryColor
import timber.log.Timber

/**
 * @created 29/06/2022 - 8:57 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegisterScreen(
    systemUiController: SystemUiController,
    onLoginClicked: () -> Unit,
    onRegisterSuccessful: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    SideEffect {
        systemUiController.setStatusBarColor(backgroundColor)
        systemUiController.setNavigationBarColor(backgroundColor)
    }

    val registerScreenState = viewModel.registerScreenState

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
                if (registerScreenState.isLoading) {
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
                                onLoginClicked()
                            }
                        )
                    }
                }

                ConnectivityStatus()

                if (registerScreenState.formError != null) {
                    LaunchedEffect(key1 = registerScreenState.formError) {
                        scaffoldState.snackbarHostState.showSnackbar(
                            registerScreenState.formError,
                            duration = SnackbarDuration.Short
                        )
                    }
                }

                if (registerScreenState.error != null) {
                    LaunchedEffect(key1 = registerScreenState.error) {
                        scaffoldState.snackbarHostState.showSnackbar(
                            registerScreenState.error,
                            duration = SnackbarDuration.Short
                        )
                    }
                }

                if (registerScreenState.successful) {
                    val openDialog = remember { mutableStateOf(true) }
                    if (openDialog.value) {
                        CustomAlertDialog(onDismissRequest = {
                            openDialog.value = false
                            onRegisterSuccessful()
                        })
                    }
                }
            }
        }
    )
}