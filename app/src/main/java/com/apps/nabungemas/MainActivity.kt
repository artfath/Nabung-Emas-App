package com.apps.nabungemas

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.apps.nabungemas.databinding.ActivityMainBinding
import kotlin.text.Typography.dagger


class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        val navHostFragment=supportFragmentManager
//            .findFragmentById(R.id.fragment_container_view) as NavHostFragment
//        val navigationTitle=navHostFragment.navController
//        setupActionBarWithNavController(navigationTitle)


//        navController = findNavController(R.id.fragment_container_view)
//        binding.bottomNavigation.setupWithNavController(navController)
//
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//                R.id.addTransactionFragment ->
//                    binding.bottomNavigation.visibility = View.GONE
//                else ->
//                    binding.bottomNavigation.visibility = View.VISIBLE
//            }
//        }
    }


}

