package com.larkes.firebasecryptoportfoliosample.features.auth.ui.auth


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.larkes.firebasecryptoportfoliosample.features.auth.presentation.AuthViewModel
import com.larkes.firebasecryptoportfoliosample.features.auth.presentation.models.AuthUIAction
import com.larkes.firebasecryptoportfoliosample.navigation.NavigationTree
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthViewModel = koinViewModel()
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