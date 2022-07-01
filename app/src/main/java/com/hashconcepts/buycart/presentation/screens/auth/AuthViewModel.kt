package com.hashconcepts.buycart.presentation.screens.auth

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Constraints
import androidx.lifecycle.ViewModel
import com.hashconcepts.buycart.data.local.SharedPrefUtil
import com.hashconcepts.buycart.data.remote.dto.request.LoginDto
import com.hashconcepts.buycart.domain.usecases.LoginUserUseCase
import com.hashconcepts.buycart.presentation.screens.auth.login.LoginFormState
import com.hashconcepts.buycart.presentation.screens.auth.login.LoginScreenEvents
import com.hashconcepts.buycart.presentation.screens.auth.login.LoginScreenState
import com.hashconcepts.buycart.utils.Constants
import com.hashconcepts.buycart.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {

    var loginScreenState = mutableStateOf(LoginScreenState())
        private set

    val isFirstAppLaunch = sharedPrefUtil.isFirstAppLaunch()

    fun saveFirstAppLaunch(value: Boolean) = sharedPrefUtil.saveFirstAppLaunch(value)

    fun saveUserAccessToken(token: String) = sharedPrefUtil.saveUserAccessToken(token)

    fun onLoginAction(events: LoginScreenEvents) {
        when(events) {
            is LoginScreenEvents.UsernameEntered -> {
                loginScreenState.value = LoginScreenState(loginFormState = LoginFormState(username = events.username))
            }
            is LoginScreenEvents.PasswordEntered -> {
                loginScreenState.value = LoginScreenState(loginFormState = LoginFormState(password = events.password))
            }
            is LoginScreenEvents.LoginClicked -> {
                val username = loginScreenState.value.loginFormState?.username
                val password = loginScreenState.value.loginFormState?.password

                if (username != null && password != null) {
                    val result = LoginUserUseCase.validateLoginRequest(username, password)

                    if (result.error != null) {
                        loginScreenState.value = LoginScreenState(loginFormState = LoginFormState(formError = result.error))
                    } else {
                        loginUser(username, password)
                    }
                }
            }
            is LoginScreenEvents.RegisterClicked -> {
                loginScreenState.value = LoginScreenState(register = true)
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
                    loginScreenState.value = LoginScreenState(successful = true)
                }
            }
        }
    }

}