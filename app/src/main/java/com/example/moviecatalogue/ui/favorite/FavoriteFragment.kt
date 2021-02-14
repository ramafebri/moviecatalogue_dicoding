package com.example.moviecatalogue.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.moviecatalogue.databinding.FragmentFavoriteBinding
import com.example.moviecatalogue.ui.pager.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout

class FavoriteFragment : Fragment() {
    private lateinit var fragmentFavoriteBinding: FragmentFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        fragmentFavoriteBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        val sectionsPagerAdapter = SectionsPagerAdapter(requireActivity(), parentFragmentManager)
        val viewPager: ViewPager = fragmentFavoriteBinding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = fragmentFavoriteBinding.tabs
        tabs.setupWithViewPager(viewPager)
    }

}