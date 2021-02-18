package com.example.moviecatalogue.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.databinding.FragmentHomeBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class HomeFragment : Fragment() {
    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        fragmentHomeBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
            val homeAdapter = HomeAdapter()

            viewModel.setPlayingMovies()
            viewModel.getPlayingMovies().observe(viewLifecycleOwner, { movies ->
                homeAdapter.setMovies(movies)
                homeAdapter.notifyDataSetChanged()
                fragmentHomeBinding.pgrBarMovies.isVisible = false
            })

            with(fragmentHomeBinding.rvHomeFragment) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = homeAdapter
            }
        }
    }

    override fun onDestroyView() {
        fragmentHomeBinding
        super.onDestroyView()
    }
}