package com.example.moviecatalogue.ui.favorite.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.databinding.FragmentFavoriteMoviesBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class FavoriteMoviesFragment : Fragment() {
    private lateinit var fragmentFavoriteMoviesBinding: FragmentFavoriteMoviesBinding
    private lateinit var viewModel: FavoriteMoviesViewModel
    private var favMoviesAdapter = FavoriteMoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFavoriteMoviesBinding.inflate(inflater, container, false)
        fragmentFavoriteMoviesBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteMoviesViewModel::class.java]
            // val favMoviesAdapter = FavoriteMoviesAdapter()

//            viewModel.getFavMovies().observe(viewLifecycleOwner, { movies ->
//                favMoviesAdapter.setFavMovies(movies)
//                favMoviesAdapter.notifyDataSetChanged()
//            })

            with(fragmentFavoriteMoviesBinding.rvFavMoviesFragment) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favMoviesAdapter
            }
        }
        if(savedInstanceState == null){
            getData()
        }
    }

    private fun getData(){
        viewModel.getFavMovies().observe(viewLifecycleOwner, { movies ->
            favMoviesAdapter.setFavMovies(movies)
            favMoviesAdapter.notifyDataSetChanged()
        })
    }
}