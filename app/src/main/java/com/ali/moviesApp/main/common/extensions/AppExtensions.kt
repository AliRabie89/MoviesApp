

package com.ali.moviesApp.main.common.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.ali.moviesApp.R

/**Print log*/
fun Any.showVLog(log: String, tag: String = getClassName()) = Log.v(tag, log)

fun Any.showELog(log: String, tag: String = getClassName()) = Log.e(tag, log)

fun Any.showDLog(log: String, tag: String = getClassName()) = Log.d(tag, log)

fun Any.showILog(log: String, tag: String = getClassName()) = Log.i(tag, log)

fun Any.showWLog(log: String, tag: String = getClassName()) = Log.w(tag, log)

fun Any.toJson(any: Any)= Gson().toJson(any)!!

inline fun <reified T> String.parseJson(): T {
    val gson = Gson()
    return gson.fromJson(this, T::class.java)
}
