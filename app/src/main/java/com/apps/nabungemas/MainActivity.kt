package com.apps.nabungemas

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.apps.nabungemas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val navHostFragment=supportFragmentManager
//            .findFragmentById(R.id.fragment_container_view) as NavHostFragment
//        val navigationTitle=navHostFragment.navController
//        setupActionBarWithNavController(navigationTitle)


        navController = findNavController(R.id.fragment_container_view)
        binding.bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.addTransactionFragment ->
                    binding.bottomNavigation.visibility = View.GONE
                else ->
                    binding.bottomNavigation.visibility = View.VISIBLE
            }
        }
    }


}