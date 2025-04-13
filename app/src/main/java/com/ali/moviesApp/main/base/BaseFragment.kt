

package com.ali.moviesApp.main.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import android.view.LayoutInflater

@AndroidEntryPoint
abstract class BaseFragment(@LayoutRes val resId: Int?) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return resId?.let {
            inflater.inflate(resId, container, false)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpLayoutView()
    }


    protected abstract fun setUpLayoutView()

}