package com.erkan.themoviedb.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.erkan.themoviedb.service.model.Category
import com.erkan.themoviedb.ui.movies.MovieListFragment

class CategoryPagerAdapter(
    fragmentManager: FragmentManager,
    val list: List<Category>
) : FragmentStatePagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment {
        val fragment = MovieListFragment()
        val bundle = Bundle()
        bundle.putInt("category_id", list.get(position).id)
        fragment.arguments = bundle
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list.get(position).name
    }

    override fun getCount(): Int = list.size
}
