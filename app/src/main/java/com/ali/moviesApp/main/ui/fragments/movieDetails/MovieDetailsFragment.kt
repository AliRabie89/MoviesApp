package com.ali.moviesApp.main.ui.fragments.movieDetails

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ali.moviesApp.R
import com.ali.moviesApp.databinding.FragmentMovieDetailsBinding
import com.ali.moviesApp.main.base.BaseFragment
import com.ali.moviesApp.main.data.remote.dtos.EndPointResult
import com.ali.moviesApp.main.domain.uiModels.MovieUiModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MovieDetailsFragment : BaseFragment(R.layout.fragment_movie_details) {
    private val args: MovieDetailsFragmentArgs by navArgs()
    lateinit var binding : FragmentMovieDetailsBinding
    private val viewModel : MovieViewModel by viewModels()
    private var movie : MovieUiModel? = null
    
    override fun setUpLayoutView() {
        binding = FragmentMovieDetailsBinding.bind(requireView())
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
        binding.transactionName = args.movieId
        
        initUi()
    }

    private fun initUi() {
        binding.backBtn.setOnClickListener { findNavController().popBackStack() }
        binding.btnFavorite.setOnCheckedChangeListener { button, status ->
            if(button.isPressed){
                movie.let {
                    viewModel.addMovieToFavorites(it!!,status)
                }
            }
        }
        lifecycleScope.launch { 
           viewModel.movieFlow.collectLatest { 
               when(it){
                   is EndPointResult.Error -> {}
                   is EndPointResult.Loading -> {}
                   is EndPointResult.Success -> {
                       binding.movie = it.result
                       movie = it.result
                   }
               }
           }
        }
        viewModel.getMovie(args.movieId.toInt())
    }


}