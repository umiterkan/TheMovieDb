package com.erkan.themoviedb.ui.category

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.erkan.themoviedb.R
import com.erkan.themoviedb.databinding.FragmentCategoryMainBinding
import com.erkan.themoviedb.di.ViewModelFactory
import com.erkan.themoviedb.ui.base.BaseFragment
import com.erkan.themoviedb.ui.splash.SplashActivity
import com.erkan.themoviedb.viewmodel.CategoryMainViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class CategoryMainFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var providerSharedPreferences: SharedPreferences


    private lateinit var viewModel: CategoryMainViewModel

    private lateinit var binding: FragmentCategoryMainBinding

    private var adapterTabLayout: CategoryPagerAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_category_main, container, false)
        NavigationUI.setupWithNavController(binding.toolbarCategoryMain, findNavController())
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CategoryMainViewModel::class.java)
        initObserve()
        initListener()
    }

    private fun initListener() {

        binding.buttonExitApp.setOnClickListener {
            providerSharedPreferences.edit().clear().apply()
            startActivity(Intent(context, SplashActivity::class.java))
            activity?.finish()
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun initObserve() {
        viewModel.getCategoryList().observe(this, Observer {
            it?.let {
                if (!it.genres.isNullOrEmpty()) {
                    binding.loading = true
                    adapterTabLayout =
                        CategoryPagerAdapter(activity!!.supportFragmentManager, it.genres)
                    binding.viewpagerMovie.adapter = adapterTabLayout
                    binding.tablayoutCategoryMain.setupWithViewPager(binding.viewpagerMovie)
                }
            }

        })

        viewModel.error.observe(this, Observer {
            showError(getString(R.string.error))
        })
    }




}