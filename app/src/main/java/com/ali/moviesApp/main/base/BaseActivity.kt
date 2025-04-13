

package com.ali.moviesApp.main.base

import android.app.Activity
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale


@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {
    lateinit var myApp: BaseApp



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myApp = this.applicationContext as BaseApp
        setContentView(setUpLayoutView());
    }

    protected abstract fun setUpLayoutView(): View



    override fun onDestroy() {
        clearReferences()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        myApp.setCurrentActivity(this)
    }

    //Check if in chat activity
    private fun clearReferences() {
        try {
            val currActivity: Activity = myApp.getCurrentActivity()!!
            if (this == currActivity) myApp.setCurrentActivity(null)
        } catch (_: NullPointerException) {
        }
    }

    override fun onPause() {
        super.onPause()
        clearReferences()
    }


    open fun setLocale(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val resources: Resources = resources
        val configuration: Configuration = resources.configuration
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }

}