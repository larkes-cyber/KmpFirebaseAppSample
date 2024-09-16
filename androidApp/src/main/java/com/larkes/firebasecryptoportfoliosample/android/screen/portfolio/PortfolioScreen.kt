package com.larkes.firebasecryptoportfoliosample.android.screen.portfolio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.larkes.firebasecryptoportfoliosample.android.navigation.NavigationTree
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.portfolio.PortfolioViewModel
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.portfolio.models.PortfolioUIAction
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.portfolio.models.PortfolioUIState

@Composable
fun PortfolioScreen(
    navController: NavController,
    viewModel: PortfolioViewModel
) {


    val uiState by viewModel.uiState.collectAsState(initial = PortfolioUIState())
    val uiAction by viewModel.uiAction.collectAsState(initial = PortfolioUIAction.None)

    PortfolioView(uiState = uiState){
        viewModel.onEvent(it)
    }

    when(uiAction){
        is PortfolioUIAction.OpenAddCoinScreen -> {
            navController.navigate(NavigationTree.AddCoin.name)
        }
        else -> {}
    }


}