

package com.ali.moviesApp.main.common.extensions

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.TranslateAnimation


fun View.slideDownFromUp() {
    visibility = View.VISIBLE
    val animate = TranslateAnimation(
        0f, // fromXDelta
        0f, // toXDelta
        -height.toFloat(), // fromYDelta
        0f
    )                // toYDelta
    animate.duration = 1000
    animate.fillAfter = true
    startAnimation(animate)
}

fun View.slideUpFromDown() {
    val animate = TranslateAnimation(
        0f, // fromXDelta
        0f, // toXDelta
        height.toFloat(), // fromYDelta
        0f
    ) // toYDelta
    animate.duration = 1500
    animate.fillAfter = true
    startAnimation(animate)
}


fun View.fadeInAnimation() {
    val alphaAnimation = AlphaAnimation(0.0f, 1.0f)
    alphaAnimation.duration = 1500
    alphaAnimation.repeatCount = 0
    startAnimation(alphaAnimation)
    visibility = View.VISIBLE
}


fun View.fadeOutAnimation() {
    val alphaAnimation = AlphaAnimation(1.0f, 0.0f)
    alphaAnimation.duration = 100
    alphaAnimation.repeatCount = 0
    startAnimation(alphaAnimation)
    visibility = View.GONE
}


fun View.slideUpFromDownExt() {
    val animate = TranslateAnimation(
        0f, // fromXDelta
        0f, // toXDelta
        0f, // fromYDelta
        -height.toFloat()
    ) // toYDelta
    animate.duration = 50
    //animate.fillAfter = true
    startAnimation(animate)
    visibility = View.GONE
}