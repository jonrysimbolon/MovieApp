package com.jonrysimbolon.testskillmovie.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.jonrysimbolon.testskillmovie.R
import com.jonrysimbolon.testskillmovie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        navHostFragment.findNavController().addOnDestinationChangedListener { _, destination, _ ->
            setTitle(destination)
            configureActionBar(destination)
            configureUpButton(destination)
        }
        backPressed()
    }

    private fun setTitle(destination: NavDestination) {
        title = destination.label
    }

    private fun configureActionBar(destination: NavDestination) {
        supportActionBar?.apply {
            if (destination.id == R.id.splashFragment) hide() else show()
        }
    }

    private fun configureUpButton(destination: NavDestination) {
        supportActionBar?.setDisplayHomeAsUpEnabled(
            !(destination.id == R.id.splashFragment || destination.id == R.id.categoryFragment)
        )
    }

    private fun backPressed() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBack()
            }
        })
    }

    fun onBack() {
        navHostFragment.findNavController().let {
            when (it.currentDestination?.id) {
                R.id.splashFragment -> {}
                R.id.categoryFragment -> finish()
                else -> it.popBackStack()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBack()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}