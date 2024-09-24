package com.larkes.firebasecryptoportfoliosample.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.larkes.firebasecryptoportfoliosample.features.auth.ui.auth.AuthScreen
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.portfolio.PortfolioViewModel
import com.larkes.firebasecryptoportfoliosample.features.portfolio.ui.add.AddCoinScreen
import com.larkes.firebasecryptoportfoliosample.features.portfolio.ui.portfolio.PortfolioScreen


@Composable
fun Navigation(navHostController: NavHostController) {

    NavHost(
        navHostController,
        startDestination = NavigationTree.Auth.name,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        }
    ){

        composable(route = NavigationTree.Auth.name){
            AuthScreen(navHostController)
        }
        composable(route = NavigationTree.Portfolio.name){
            PortfolioScreen(
               navController =  navHostController
            )
        }
        composable(route = NavigationTree.AddCoin.name){
            AddCoinScreen(navController = navHostController)
        }


    }

}