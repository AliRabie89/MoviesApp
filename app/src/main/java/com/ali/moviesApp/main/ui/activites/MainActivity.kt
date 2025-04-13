package com.ali.moviesApp.main.ui.activites

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ali.moviesApp.R
import com.ali.moviesApp.databinding.MainActivityBinding
import com.ali.moviesApp.main.base.BaseActivity


class MainActivity : BaseActivity() {
    lateinit var binding : MainActivityBinding

    override fun setUpLayoutView(): View {
        binding = MainActivityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.moviesFragment,
                R.id.favoritesFragment -> binding.bottomNav.visibility = View.VISIBLE
                else -> binding.bottomNav.visibility = View.GONE
            }
        }
    }
}