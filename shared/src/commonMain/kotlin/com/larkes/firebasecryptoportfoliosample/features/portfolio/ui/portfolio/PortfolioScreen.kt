package com.larkes.firebasecryptoportfoliosample.features.portfolio.ui.portfolio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.portfolio.PortfolioViewModel
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.portfolio.models.PortfolioUIAction
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.portfolio.models.PortfolioUIState
import com.larkes.firebasecryptoportfoliosample.navigation.NavigationTree
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun PortfolioScreen(
    navController: NavController,
    viewModel: PortfolioViewModel = koinViewModel()
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