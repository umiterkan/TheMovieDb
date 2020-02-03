package com.erkan.themoviedb.ui.splash

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.erkan.themoviedb.R
import com.erkan.themoviedb.databinding.ActivitySplashBinding
import com.erkan.themoviedb.ui.base.BaseActivity
import com.erkan.themoviedb.ui.main.MainActivity
import com.erkan.themoviedb.util.Constants.Companion.APP_LANGUAGE
import com.erkan.themoviedb.util.Constants.Companion.LANGUAGE_ENG
import com.erkan.themoviedb.util.Constants.Companion.LANGUAGE_TR
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashActivity : BaseActivity(),HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> =dispatchingAndroidInjector

    @Inject
    lateinit var providerSharedPreferences: SharedPreferences


    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.loading = true

        GlobalScope.launch(context = Dispatchers.Main) {
            delay(2000)
            languageControl()

        }

        binding.buttonEngLanguage.setOnClickListener {
            providerSharedPreferences.edit().putString(APP_LANGUAGE, LANGUAGE_ENG).apply()
            actionMainActivity()
        }
        binding.buttonTrLanguage.setOnClickListener {
            providerSharedPreferences.edit().putString(APP_LANGUAGE, LANGUAGE_TR).apply()
            actionMainActivity()
        }
    }

    private fun languageControl() {
        val appLanguage = providerSharedPreferences.getString(APP_LANGUAGE, null)
        if (appLanguage == null) {
            binding.loading = false
        } else {
            actionMainActivity()
        }
    }

    private fun actionMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}