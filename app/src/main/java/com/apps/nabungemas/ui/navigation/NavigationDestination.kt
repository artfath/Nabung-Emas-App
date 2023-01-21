package com.apps.nabungemas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

interface NavigationDestination{
    val route :String
    val title :Int
}