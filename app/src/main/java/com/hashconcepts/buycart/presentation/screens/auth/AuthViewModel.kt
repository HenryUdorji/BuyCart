package com.hashconcepts.buycart.presentation.screens.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hashconcepts.buycart.data.local.SharedPrefUtil
import com.hashconcepts.buycart.domain.usecases.LoginUserUseCase
import com.hashconcepts.buycart.domain.usecases.RegisterUserUseCase
import com.hashconcepts.buycart.presentation.screens.auth.login.LoginFormState
import com.hashconcepts.buycart.presentation.screens.auth.login.LoginScreenState
import com.hashconcepts.buycart.presentation.screens.auth.register.RegisterFormState
import com.hashconcepts.buycart.presentation.screens.auth.register.RegisterScreenState
import com.hashconcepts.buycart.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * @created 28/06/2022 - 2:08 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val sharedPrefUtil: SharedPrefUtil,
    private val loginUserUseCase: LoginUserUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
) : ViewModel() {

    var loginScreenState = mutableStateOf(LoginScreenState())
        private set

    var registerScreenState = mutableStateOf(RegisterScreenState())
        private set

    val isFirstAppLaunch = sharedPrefUtil.isFirstAppLaunch()

    fun saveFirstAppLaunch(value: Boolean) = sharedPrefUtil.saveFirstAppLaunch(value)

    private fun saveUserAccessToken(token: String) = sharedPrefUtil.saveUserAccessToken(token)

    fun onAuthAction(events: AuthScreenEvents) {
        when(events) {
            is AuthScreenEvents.LoginClicked -> {
                val username = events.username
                val password = events.password

                val result = LoginUserUseCase.validateLoginRequest(username, password)

                if (result.successful) {
                    loginUser(username, password)
                } else {
                    loginScreenState.value = LoginScreenState(loginFormState = LoginFormState(formError = result.error))
                }
            }
            is AuthScreenEvents.RegisterClicked -> {
                val username = events.username
                val password = events.password
                val email = events.email
                val phoneNo = events.phoneNo

                val result = RegisterUserUseCase.validateRegisterRequest(username, password, email, phoneNo)
                if (result.successful) {
                    registerUser(username, password, email, phoneNo)
                } else {
                    registerScreenState.value = RegisterScreenState(registerFormState = RegisterFormState(formError = result.error))
                }
            }
        }
    }

    private fun loginUser(username: String, password: String) {
        loginUserUseCase(username, password).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    loginScreenState.value = LoginScreenState(isLoading = true)
                }
                is Resource.Error -> {
                    loginScreenState.value = LoginScreenState(error = result.message)
                }
                is Resource.Success -> {
                    saveUserAccessToken(result.data?.token!!)
                    loginScreenState.value = LoginScreenState(successful = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun registerUser(
        username: String,
        password: String,
        email: String,
        phone: String
    ) {
        registerUserUseCase(username, password, email, phone).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    registerScreenState.value = RegisterScreenState(isLoading = true)
                }
                is Resource.Error -> {
                    registerScreenState.value = RegisterScreenState(error = result.message)
                }
                is Resource.Success -> {
                    registerScreenState.value = RegisterScreenState(successful = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}