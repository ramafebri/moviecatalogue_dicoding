package com.example.moviecatalogue.ui.favorite.tv

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
import com.example.moviecatalogue.data.source.local.entity.TvShowsEntity
import com.example.moviecatalogue.databinding.FragmentFavoriteTvBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class FavoriteTvFragment : Fragment() {
    private lateinit var fragmentFavoriteTvBinding: FragmentFavoriteTvBinding
    private lateinit var viewModel: FavoriteTvViewModel
    private lateinit var favTvAdapter: FavoriteTvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFavoriteTvBinding.inflate(inflater, container, false)
        fragmentFavoriteTvBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(fragmentFavoriteTvBinding.rvFavTvFragment)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteTvViewModel::class.java]
            viewModel.getFavTv().observe(viewLifecycleOwner, getData)

            favTvAdapter = FavoriteTvAdapter()
            with(fragmentFavoriteTvBinding.rvFavTvFragment) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favTvAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavTv().observe(viewLifecycleOwner, getData)
    }

    private val getData = Observer<PagedList<TvShowsEntity>> { tv ->
        if(tv.isEmpty()){
            fragmentFavoriteTvBinding.noDataFavTv.isVisible = true
        }
        favTvAdapter.submitList(tv)
        fragmentFavoriteTvBinding.pgrBarFavTv.isVisible = false
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val tvEntity = favTvAdapter.getSwipedData(swipedPosition)
                tvEntity?.let { viewModel.delFavTv(it) }
                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v ->
                    tvEntity?.let { viewModel.insertFavTv(it) }
                    fragmentFavoriteTvBinding.noDataFavTv.isVisible = false
                }
                snackbar.show()
            }
        }
    })
}