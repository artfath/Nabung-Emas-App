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
        startDestination = BottomNavItem.Home.route,
        modifier = modifier){
        composable(route = BottomNavItem.Home.route){
            HomeScreen(navigateToAddTransaction = {navController.navigate(BottomNavItem.AddTransaction.route)})
        }
        composable(route = BottomNavItem.Transaction.route){
            TransactionScreen(navigateToAddTransaction = {navController.navigate(BottomNavItem.AddTransaction.route)})
        }
        composable(route = GraphDestination.route){
            GraphScreen()
        }
        composable(route = SavingDestination.route){
            SavingScreen(navigateToAddSaving = {navController.navigate(BottomNavItem.AddSaving.route)})
        }
        composable(route = AboutDestination.route){
            AboutScreen()
        }
        composable(route = BottomNavItem.AddTransaction.route){
            AddTransactionScreen(
                navigateBack = {navController.popBackStack()},
                onNavigateUp = {navController.navigateUp()})
        }
        composable(route = BottomNavItem.AddSaving.route){
            AddSavingScreen(navigateBack = {navController.popBackStack()},
                onNavigateUp = {navController.navigateUp()})
        }
    }
}