package com.larkes.firebasecryptoportfoliosample

import androidx.compose.ui.window.ComposeUIViewController
import androidx.navigation.compose.rememberNavController
import com.larkes.firebasecryptoportfoliosample.features.auth.ui.auth.AuthScreen
import com.larkes.firebasecryptoportfoliosample.navigation.Navigation
import com.larkes.firebasecryptoportfoliosample.theme.AppTheme

fun MainViewController() = ComposeUIViewController {
    val navController = rememberNavController()
    AppTheme {
        Navigation(navController)
    }
}