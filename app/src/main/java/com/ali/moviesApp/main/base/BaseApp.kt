

package com.ali.moviesApp.main.base

import android.app.Activity
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp: Application(){

    override fun onCreate() {
        super.onCreate()
//        FirebaseApp.initializeApp(this)
    }

    private var mCurrentActivity: Activity? = null
    fun getCurrentActivity(): Activity? {
        return mCurrentActivity
    }

    fun setCurrentActivity(mCurrentActivity: Activity?) {
        this.mCurrentActivity = mCurrentActivity
    }
}