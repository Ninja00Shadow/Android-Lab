package com.example.androidlab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.bottomNavigationView)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        bottomNavigation.selectedItemId = R.id.mainFragment

//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.leftFragment,
//                R.id.mainFragment,
//                R.id.rightFragment
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigation.setupWithNavController(navController)

//        bottomNavigation.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.leftFragment -> {
//                    navController.navigate(R.id.actionGlobalToLeftFragment)
//                    true
//                }
//                R.id.rightFragment -> {
//                    navController.navigate(R.id.actionGlobalToRightFragment)
//                    true
//                }
//                R.id.mainFragment -> {
//                    navController.navigate(R.id.actionGlobalToMainFragment)
//                    true
//                }
//                else -> false
//
//            }
//
//        }
    }
}