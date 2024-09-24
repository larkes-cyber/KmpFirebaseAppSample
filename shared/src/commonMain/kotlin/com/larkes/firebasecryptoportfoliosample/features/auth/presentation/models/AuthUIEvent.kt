package com.larkes.firebasecryptoportfoliosample.features.auth.presentation.models

sealed class AuthUIEvent {

    data class SignInSucceeded(val idToken:String, val accessToken:String?):AuthUIEvent()

}