

package com.ali.moviesApp.main.common.extensions

import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ali.moviesApp.R
import com.ali.moviesApp.main.data.remote.dtos.NetworkState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import www.sanju.motiontoast.MotionToast

fun Any.getClassName(): String {
    return this::class.java.simpleName
}



fun <T> FragmentActivity.startActivity(_class: Class<T>) {
    startActivity(Intent(this, _class))
}


fun FragmentActivity.showMsg(msg: String, type: String) {
    MotionToast.createColorToast(
        this,
        this.getString(R.string.app_name),
        msg,
        type,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION, null
    )
}

fun FragmentActivity.showSuccessMessage(msg: String) {
    MotionToast.createColorToast(
        this,
        this.getString(R.string.app_name),
        msg,
        MotionToast.TOAST_SUCCESS,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION, null
    )
}

fun Activity.emptyListChecker(list: List<*>, view: TextView){
    if(list.isEmpty()){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

fun FragmentActivity.showErrorMessage(msg: String) {
    MotionToast.createColorToast(
        this,
        this.getString(R.string.app_name),
        msg,
        MotionToast.TOAST_ERROR,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION, null
    )
}

//fun Activity.showConfirmationDialog(title : String, message : String, positiveBtnTxt :String, negativeBtnTxt : String,onConfirm:(Boolean)->Unit) {
//    var confirmationDialog = ConfirmationDialog(this,title,message,positiveBtnTxt,negativeBtnTxt,onConfirm)
//    confirmationDialog.show()
//}



