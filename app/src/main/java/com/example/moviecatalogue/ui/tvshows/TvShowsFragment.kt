package com.example.moviecatalogue.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.databinding.FragmentTvshowsBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class TvShowsFragment : Fragment() {
    private lateinit var tvShowsBinding: FragmentTvshowsBinding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTvshowsBinding.inflate(inflater, container, false)
        tvShowsBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowsViewModel::class.java]
            val tvAdapter = TvShowsAdapter()

            viewModel.setTvShows()
            viewModel.getTvShows().observe(viewLifecycleOwner, { tv ->
                tvAdapter.setMovies(tv)
                tvAdapter.notifyDataSetChanged()
                tvShowsBinding.pgrBarTv.isVisible = false
            })

            with(tvShowsBinding.rvTvShowsFragment) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }
        }
    }

    override fun onDestroyView() {
        tvShowsBinding
        super.onDestroyView()
    }
}