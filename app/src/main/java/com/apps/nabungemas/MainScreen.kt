package com.apps.nabungemas


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.apps.nabungemas.ui.navigation.BottomNav
import com.apps.nabungemas.ui.navigation.BottomNavItem
import com.apps.nabungemas.ui.navigation.NavigationGraph
import com.apps.nabungemas.ui.theme.MyApplicationTheme

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            if(currentRoute(navController = navController)){
                BottomNav(navController = navController)
            }
            }
    )
    { innerPadding ->
        NavigationGraph(modifier = Modifier.padding(innerPadding), navController = navController)

    }
}

@Composable
fun currentRoute(navController: NavHostController): Boolean {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val route = navBackStackEntry?.destination?.route
    return when(route){
        BottomNavItem.AddTransaction.route -> false
        BottomNavItem.AddSaving.route -> false
        else->true
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    version: Int,
    navigateUp: () -> Unit ={},
    navigateAdd:()-> Unit={},
    navigateDelete:()-> Unit={}
) {
    if (version == 0) {
        CenterAlignedTopAppBar(
            title = { Text(text = title,
                color = Color.White,
                style = MaterialTheme.typography.h6) },
            modifier = modifier.fillMaxWidth(),
            navigationIcon = {
                IconButton(onClick = navigateUp ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color(0xFFffd740))

        )

    } else if (version == 1) {
        CenterAlignedTopAppBar(
            title = { Text(text = title, color = Color.White,
            style = MaterialTheme.typography.h6) },
            modifier = modifier.fillMaxWidth(),
            actions = {
                IconButton(onClick = navigateAdd) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
                IconButton(onClick = navigateDelete) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color(0xFFffd740))

        )

    } else {
        CenterAlignedTopAppBar(title = { Text(text = title, color = Color.White,
            style = MaterialTheme.typography.h6) },
            modifier = modifier,
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color(0xFFffd740)))
    }
}
@Preview(showBackground = true)
@Composable
fun MainTopAppBar(){
    MyApplicationTheme(darkTheme = false) {
        Column() {
            MainTopAppBar(title = "Top Bar", version = 0, navigateUp = {})
            MainTopAppBar(title = "Top Bar", version = 1, navigateUp = {})
            MainTopAppBar(title = "Top Bar", version = 2, navigateUp = {})
        }

    }
}