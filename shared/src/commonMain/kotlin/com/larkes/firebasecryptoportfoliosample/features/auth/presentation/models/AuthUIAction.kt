package com.larkes.firebasecryptoportfoliosample.features.auth.presentation.models

sealed class AuthUIAction {

    data object OpenPortfolioScreen:AuthUIAction()
    data object None:AuthUIAction()

}