package com.erkan.themoviedb.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showError(data: String) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
    }
}