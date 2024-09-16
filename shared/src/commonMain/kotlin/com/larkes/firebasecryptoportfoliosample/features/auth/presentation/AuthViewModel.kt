package com.larkes.firebasecryptoportfoliosample.features.auth.presentation

import CommonFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import asCommonFlow
import com.larkes.firebasecryptoportfoliosample.features.auth.domain.AuthRepository
import com.larkes.firebasecryptoportfoliosample.features.auth.presentation.models.AuthUIAction
import com.larkes.firebasecryptoportfoliosample.features.auth.presentation.models.AuthUIEvent
import com.larkes.firebasecryptoportfoliosample.utils.AuthStatus
import com.larkes.firebasecryptoportfoliosample.utils.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AuthViewModel(): ViewModel() {

    private val authRepository = Inject.di.get<AuthRepository>()

    private val _uiAction:MutableStateFlow<AuthUIAction> = MutableStateFlow(AuthUIAction.None)
    val uiAction = _uiAction.asCommonFlow()

    init {
        authRepository.checkUserAuthorization().onEach {
            if(it == AuthStatus.AUTHORIZED.name){
                _uiAction.value = AuthUIAction.OpenPortfolioScreen
            }
            return@onEach
        }.launchIn(viewModelScope)
    }

    fun onEvent(event:AuthUIEvent){
        when(event){
            is AuthUIEvent.SignInSucceeded -> {
                obtainSignInSucceeded(event.idToken, event.accessToken)
            }
        }
    }

    private fun obtainSignInSucceeded(idToken: String, accessToken:String?) {
        viewModelScope.launch {
            authRepository.performUserAuthentification(idToken = idToken, accessToken = accessToken)
            _uiAction.value = AuthUIAction.OpenPortfolioScreen
        }
    }

}