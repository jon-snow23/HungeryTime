package com.shiva.hungerytime.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shiva.hungerytime.R
import com.shiva.hungerytime.database.MealDatabase
import com.shiva.hungerytime.viewModel.HomeViewModel
import com.shiva.hungerytime.viewModel.HomeViewModelFactory

class MainActivity : AppCompatActivity() {

    val viewModel:HomeViewModel by lazy {
        val mealDatabase = MealDatabase.getInstance(this)
        val homeViewModelProviderFactory = HomeViewModelFactory(mealDatabase)
        ViewModelProvider(this , homeViewModelProviderFactory)[HomeViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navController = Navigation.findNavController(this , R.id.nav_host_fragment_container)
        NavigationUI.setupWithNavController(bottomNavigation , navController)
    }
}