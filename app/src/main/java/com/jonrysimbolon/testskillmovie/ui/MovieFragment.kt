package com.jonrysimbolon.testskillmovie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.paging.LoadState
import com.jonrysimbolon.testskillmovie.adapter.FooterLoadingStateAdapter
import com.jonrysimbolon.testskillmovie.adapter.MovieAdapter
import com.jonrysimbolon.testskillmovie.data.remote.model.MovieModel
import com.jonrysimbolon.testskillmovie.databinding.FragmentMovieBinding
import com.jonrysimbolon.testskillmovie.utils.dialog.CustomDialog
import com.jonrysimbolon.testskillmovie.viewmodel.MovieViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class MovieFragment : Fragment() {

    private val binding by lazy { FragmentMovieBinding.inflate(layoutInflater) }
    private val viewModel: MovieViewModel by activityViewModel()

    private val adapter: MovieAdapter by inject()
    private val loadingDialog: CustomDialog by inject()
    private val failureDialog: CustomDialog by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {

            val bundle = MovieFragmentArgs.fromBundle(arguments as Bundle)
            val categoryId = bundle.id.toString()
            val categoryName = bundle.name

            requireActivity().title = categoryName
            viewModel.getAllMovies(categoryId)

            viewModel.movieLivePaging.observe(viewLifecycleOwner) { movie ->
                movie?.let {
                    adapter.submitData(lifecycle, it)
                }
            }
            adapter.onClickItem = ::onClickItem
            adapter.addLoadStateListener { loadState ->
                val mediatorLoadState =
                    loadState.mediator?.refresh
                when (mediatorLoadState) {
                    is LoadState.NotLoading -> {
                        loadingDialog.show(false)
                    }

                    is LoadState.Loading -> {
                        loadingDialog.show(true)
                    }

                    is LoadState.Error -> {
                        loadingDialog.show(false)
                        failureDialog.apply {
                            show(true)
                            setDescription(mediatorLoadState.error.message.toString())
                            setReloadClickListener {
                                adapter.refresh()
                            }
                        }
                    }

                    else -> loadingDialog.show(false)
                }
            }

            rvMovie.adapter = adapter.withLoadStateFooter(
                footer = FooterLoadingStateAdapter {
                    adapter.retry()
                }
            )
        }
    }

    private fun onClickItem(viewHolder: MovieAdapter.ViewHolder, movieModel: MovieModel) {
        val toDetailMovieFragment = MovieFragmentDirections.actionMovieFragmentToDetailMovieFragment(
            movieModel.id,
            movieModel.title
        )
        viewHolder
            .itemView
            .findNavController()
            .navigate(
                toDetailMovieFragment
            )
    }
}