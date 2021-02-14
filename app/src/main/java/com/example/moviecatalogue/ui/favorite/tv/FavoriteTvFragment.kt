package com.example.moviecatalogue.ui.favorite.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.databinding.FragmentFavoriteTvBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class FavoriteTvFragment : Fragment() {
    private lateinit var fragmentFavoriteTvBinding: FragmentFavoriteTvBinding

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
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavoriteTvViewModel::class.java]
        }
    }

}