

package com.ali.moviesApp.main.common.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ali.moviesApp.R
import com.ali.moviesApp.main.data.remote.dtos.NetworkState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import www.sanju.motiontoast.MotionToast

fun Fragment.showSuccessMessage(msg: String) {
    MotionToast.createColorToast(
        activity!!,
        this.getString(R.string.app_name),
        msg,
        MotionToast.TOAST_SUCCESS,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION, null
    )
}

