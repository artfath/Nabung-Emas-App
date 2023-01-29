package com.apps.nabungemas.ui

import android.content.Context
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.apps.nabungemas.R
import com.apps.nabungemas.ui.navigation.NavItem
import com.apps.nabungemas.ui.theme.MyApplicationTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
    SplashBody(modifier = Modifier, navController = navController)

}

@Composable
fun SplashBody(modifier: Modifier,
navController: NavController) {
    val scale = remember{ Animatable(initialValue = 0f) }
    Box(modifier = modifier.fillMaxSize()
        .background(color = Color.White),
    contentAlignment = Alignment.Center) {
        LaunchedEffect(key1 = true){
            scale.animateTo(
                targetValue = 0.5f,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = {
                        OvershootInterpolator(3f).getInterpolation(it)
                    }
                )
            )
            delay(1000)
            navController.navigate(route = NavItem.Home.route){
                popUpTo(route = NavItem.Splash.route){
                    inclusive = true
                }
            }
        }
        Image(painter = painterResource(id = R.drawable.ic_saving_gold), contentDescription = null)
    }
}
@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    MyApplicationTheme(darkTheme = false) {
        SplashScreen(navController = rememberNavController())
    }
}
