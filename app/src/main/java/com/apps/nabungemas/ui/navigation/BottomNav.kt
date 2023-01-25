package com.apps.nabungemas.ui.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.apps.nabungemas.R

@Composable
fun BottomNav(navController: NavController) {
    val items = listOf(
        NavItem.Home,
        NavItem.Transaction,
        NavItem.Graph,
        NavItem.Saving,
        NavItem.About,
    )
    BottomNavigation(
        backgroundColor = Color.White,
        elevation = 2.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = screen.icon),
                        contentDescription = ""
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = screen.title),
                        softWrap = false,
                        fontSize = 10.sp
                    )
                },
                unselectedContentColor = colorResource(id = R.color.grey_200),
                selectedContentColor = colorResource(id = R.color.yellow_500),
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })

        }

    }
}