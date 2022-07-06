package com.hashconcepts.buycart.presentation.screens.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hashconcepts.buycart.data.local.SharedPrefUtil
import com.hashconcepts.buycart.domain.usecases.LoginUserUseCase
import com.hashconcepts.buycart.domain.usecases.RegisterUserUseCase
import com.hashconcepts.buycart.presentation.screens.auth.login.LoginScreenState
import com.hashconcepts.buycart.presentation.screens.auth.register.RegisterScreenState
import com.hashconcepts.buycart.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
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

    var loginScreenState by mutableStateOf(LoginScreenState())
        private set

    var registerScreenState by mutableStateOf(RegisterScreenState())
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
                    loginScreenState = loginScreenState.copy(formError = result.error)
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
                    registerScreenState = registerScreenState.copy(formError = result.error)
                }
            }
        }
    }

    private fun loginUser(username: String, password: String) {
        loginUserUseCase(username, password).onEach { result ->
            loginScreenState = when(result) {
                is Resource.Loading -> {
                    loginScreenState.copy(isLoading = true)
                }
                is Resource.Error -> {
                    loginScreenState.copy(error = result.message)
                }
                is Resource.Success -> {
                    saveUserAccessToken(result.data?.token!!)
                    loginScreenState.copy(successful = true)
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
            registerScreenState = when(result) {
                is Resource.Loading -> {
                    registerScreenState.copy(isLoading = true)
                }
                is Resource.Error -> {
                    registerScreenState.copy(error = result.message)
                }
                is Resource.Success -> {
                    registerScreenState.copy(successful = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}