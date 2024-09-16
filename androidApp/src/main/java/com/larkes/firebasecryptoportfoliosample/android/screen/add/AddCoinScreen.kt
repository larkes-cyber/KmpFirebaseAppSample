package com.larkes.firebasecryptoportfoliosample.android.screen.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.larkes.firebasecryptoportfoliosample.android.navigation.NavigationTree
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.add.AddCoinViewModel
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.add.models.AddCoinUIAction
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.add.models.AddCoinUIState

@Composable
fun AddCoinScreen(
    viewModel: AddCoinViewModel = viewModel(),
    navController: NavController
) {

    val uiState by viewModel.uiState.collectAsState(initial = AddCoinUIState())
    val uiAction by viewModel.uiAction.collectAsState(initial = AddCoinUIAction.None)

    AddCoinView(uiState = uiState){
        viewModel.onEvent(it)
    }

    when(uiAction){
        is AddCoinUIAction.OpenPortfolioScreen -> {
            navController.navigate(NavigationTree.Portfolio.name)
        }
        else -> {}
    }

}