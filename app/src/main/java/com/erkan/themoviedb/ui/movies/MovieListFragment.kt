package com.erkan.themoviedb.ui.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.erkan.themoviedb.R
import com.erkan.themoviedb.databinding.FragmentScreenMovieBinding
import com.erkan.themoviedb.di.ViewModelFactory
import com.erkan.themoviedb.service.model.Movie

import com.erkan.themoviedb.ui.base.BaseFragment
import com.erkan.themoviedb.ui.category.CategoryMainFragmentDirections
import com.erkan.themoviedb.viewmodel.MovieListViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MovieListFragment : BaseFragment(), MovieItemListenerInterface {

    override fun itemMovieListener(movie: Movie) {
        val action =
            CategoryMainFragmentDirections.actionCategoryMainFragmentToMovieDetailFragment(movie)
        findNavController().navigate(action)
    }


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: MovieListViewModel

    lateinit var binding: FragmentScreenMovieBinding

    private var adapter: MovieListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_screen_movie, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)
        binding.loading = true
        val categoryId = arguments?.getInt("category_id")
        adapter = MovieListAdapter(this)
        binding.recyclerViewMovieList.layoutManager =
            GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewMovieList.adapter = adapter

        viewModel.getMoviewList(categoryId!!).observe(this, Observer {
            binding.loading = false
            adapter?.list = it
            adapter?.notifyDataSetChanged()
        })

        viewModel.error.observe(this, Observer {
            showError(getString(R.string.error))
        })
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }


}
