package com.example.moviecatalogue.ui.favorite.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.databinding.FragmentFavoriteMoviesBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class FavoriteMoviesFragment : Fragment() {
    private lateinit var fragmentFavoriteMoviesBinding: FragmentFavoriteMoviesBinding
    private lateinit var viewModel: FavoriteMoviesViewModel
    private lateinit var favMoviesAdapter : FavoriteMoviesAdapter

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
        itemTouchHelper.attachToRecyclerView(fragmentFavoriteMoviesBinding.rvFavMoviesFragment)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteMoviesViewModel::class.java]
            viewModel.getFavMovies().observe(viewLifecycleOwner, getData)

            favMoviesAdapter = FavoriteMoviesAdapter()

            with(fragmentFavoriteMoviesBinding.rvFavMoviesFragment) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favMoviesAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavMovies().observe(viewLifecycleOwner, getData)
    }

    private val getData = Observer<PagedList<MoviesEntity>> { movies ->
        if(movies.isEmpty()){
            fragmentFavoriteMoviesBinding.noDataFavMovies.isVisible = true
        }
        favMoviesAdapter.submitList(movies)
        fragmentFavoriteMoviesBinding.pgrBarFavMovies.isVisible = false
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val moviesEntity = favMoviesAdapter.getSwipedData(swipedPosition)
                moviesEntity?.let { viewModel.delFavMovies(it) }
                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { _ ->
                    moviesEntity?.let { viewModel.insertFavMovies(it) }
                    fragmentFavoriteMoviesBinding.noDataFavMovies.isVisible = false
                }
                snackbar.show()
            }
        }
    })
}