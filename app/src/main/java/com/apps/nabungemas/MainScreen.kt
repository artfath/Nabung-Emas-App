package com.apps.nabungemas


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.apps.nabungemas.ui.theme.MyApplicationTheme

@Composable
fun MainScreen() {
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    navigateBack: Int,
    navigateUp: () -> Unit = {}
) {
    if (navigateBack == 0) {
        CenterAlignedTopAppBar(
            title = { Text(text = title) },
            modifier = modifier.fillMaxWidth(),
            navigationIcon = {
                IconButton(onClick = { navigateUp }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors()

        )

    } else if (navigateBack == 1) {
        CenterAlignedTopAppBar(
            title = { Text(text = title, color = Color.White) },
            modifier = modifier.fillMaxWidth(),
            actions = {
                IconButton(onClick = {  }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
                IconButton(onClick = {  }) {
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
        CenterAlignedTopAppBar(title = { Text(text = title) }, modifier = modifier)
    }
}
@Preview(showBackground = true)
@Composable
fun MainTopAppBar(){
    MyApplicationTheme(darkTheme = false) {
        Column() {
            MainTopAppBar(title = "Top Bar", navigateBack = 0)
            MainTopAppBar(title = "Top Bar", navigateBack = 1)
            MainTopAppBar(title = "Top Bar", navigateBack = 2)
        }

    }
}