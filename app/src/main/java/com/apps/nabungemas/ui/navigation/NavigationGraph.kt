package com.apps.nabungemas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.apps.nabungemas.ui.*

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController,
        startDestination = NavItem.Splash.route,
        modifier = modifier){
        composable(route = NavItem.Splash.route){
            SplashScreen(navController = navController)
        }
        composable(route = NavItem.Home.route){
            HomeScreen(navigateToAddTransaction = {navController.navigate(NavItem.AddTransaction.route)})
        }
        composable(route = NavItem.Transaction.route){
            TransactionScreen(navigateToAddTransaction = {navController.navigate(NavItem.AddTransaction.route)})
        }
        composable(route = NavItem.Graph.route){
            GraphScreen()
        }
        composable(route = NavItem.Saving.route){
            SavingScreen(navigateToAddSaving = {navController.navigate(NavItem.AddSaving.route)})
        }
        composable(route = NavItem.About.route){
            AboutScreen()
        }
        composable(route = NavItem.AddTransaction.route){
            AddTransactionScreen(
                navigateBack = {navController.popBackStack()},
                onNavigateUp = {navController.navigateUp()})
        }
        composable(route = NavItem.AddSaving.route){
            AddSavingScreen(navigateBack = {navController.popBackStack()},
                onNavigateUp = {navController.navigateUp()})
        }
    }
}