package com.erkan.themoviedb.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showError(data: String) {
        if (activity != null && activity is BaseActivity) {
            val baseActivity = activity as BaseActivity
            baseActivity.showError(data)
        }

    }
}