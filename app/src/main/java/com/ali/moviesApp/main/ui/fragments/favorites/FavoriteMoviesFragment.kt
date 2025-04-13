package com.ali.moviesApp.main.ui.fragments.favorites


import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ali.moviesApp.R
import com.ali.moviesApp.databinding.FragmentFavoriteMoviesBinding
import com.ali.moviesApp.main.base.BaseFragment
import com.ali.moviesApp.main.data.remote.dtos.EndPointResult
import com.ali.moviesApp.main.ui.fragments.movies.MoviesAdapter
import com.ali.moviesApp.main.ui.fragments.movies.MoviesListFragmentDirections
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FavoriteMoviesFragment : BaseFragment(R.layout.fragment_favorite_movies) {
    private var _binding : FragmentFavoriteMoviesBinding? = null
    private val viewModel : FavoritesViewModel by viewModels()
    private lateinit var movesAdapter : MoviesAdapter
    private val binding get() = _binding!!
    private var isGrid = false


    override fun setUpLayoutView() {
        _binding = FragmentFavoriteMoviesBinding.bind(requireView())
        initUi()
        initData()
    }

    private fun initData() {
        lifecycleScope.launch {
            viewModel.moviesFlow.collectLatest {
                when(it){
                    is EndPointResult.Error -> {}
                    is EndPointResult.Loading -> {}
                    is EndPointResult.Success -> {
                        if(it.result.isNotEmpty()){
                            binding.emptyFlag.visibility = View.GONE
                        }else{
                            binding.emptyFlag.visibility = View.VISIBLE
                        }
                        movesAdapter.setData(it.result)
                    }
                }
            }
        }
        viewModel.getFavorites()
    }



    private fun initUi() {
        movesAdapter = MoviesAdapter(isGrid,
            onItemClicked = {movie,poster ->
                val action = MoviesListFragmentDirections
                    .actionMovieListToMovieDetails(movieId = movie.id)
                val extras = FragmentNavigatorExtras(
                    poster to movie.id
                )
                findNavController().navigate(action, extras)
            }
            ,
            onAddToFavoriteButtonClicked = {movieUiModel, states ->
            viewModel.removeMovieToFavorites(movieUiModel,states)
        })
        binding.moviesList.apply {
            adapter = movesAdapter
        }
        setLayoutManager()
        binding.layoutToggle.setOnCheckedChangeListener { _, isChecked ->
            isGrid = isChecked
            val currentPosition = (binding.moviesList.layoutManager as? LinearLayoutManager)
                ?.findFirstVisibleItemPosition() ?: 0

            val newLayoutManager = if (isGrid) {
                GridLayoutManager(requireContext(), 2)
            } else {
                LinearLayoutManager(requireContext())
            }
            binding.moviesList.layoutManager = newLayoutManager
            movesAdapter.setLayoutStyle(isGrid)
            binding.moviesList.scrollToPosition(currentPosition)
        }
    }

    private fun setLayoutManager() {
        binding.moviesList.layoutManager = if (isGrid) {
            GridLayoutManager(requireContext(), 2)
        } else {
            LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clear the binding reference to prevent memory leaks
    }

}