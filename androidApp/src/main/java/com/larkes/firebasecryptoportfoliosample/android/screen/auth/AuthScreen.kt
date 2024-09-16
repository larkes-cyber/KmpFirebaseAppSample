package com.larkes.firebasecryptoportfoliosample.android.screen.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.larkes.firebasecryptoportfoliosample.android.navigation.NavigationTree
import com.larkes.firebasecryptoportfoliosample.features.auth.presentation.AuthViewModel
import com.larkes.firebasecryptoportfoliosample.features.auth.presentation.models.AuthUIAction

@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel{AuthViewModel() }
) {

    val uiAction by viewModel.uiAction.collectAsState(initial = AuthUIAction.None)

    when(uiAction){
        AuthUIAction.OpenPortfolioScreen -> {
            navController.navigate(NavigationTree.Portfolio.name)
        }
        else -> {}
    }

    AuthView(){
        viewModel.onEvent(it)
    }

}