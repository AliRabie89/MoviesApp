

package com.ali.moviesApp.main.common.extensions

import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.widget.AppCompatEditText
import java.util.Timer
import java.util.TimerTask

fun AppCompatEditText.addDebouncedTextChangedListener(onSearchDone: (String) -> Unit) {
    var timer = Timer()
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            // Not used in this context
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            timer.cancel()
            timer = Timer()
            timer.schedule(
                object : TimerTask() {
                    override fun run() {
                        // Run on the UI thread
                        post {
                            onSearchDone(s.toString())
                        }
                    }
                },
                200 // delay in milliseconds before task is to be executed
            )
        }

        override fun afterTextChanged(s: Editable) {
            // Not used in this context
        }
    })
}