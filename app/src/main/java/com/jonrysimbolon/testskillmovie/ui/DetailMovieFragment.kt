package com.jonrysimbolon.testskillmovie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.jonrysimbolon.testskillmovie.R
import com.jonrysimbolon.testskillmovie.data.remote.model.DetailMovieModel
import com.jonrysimbolon.testskillmovie.databinding.FragmentDetailMovieBinding
import com.jonrysimbolon.testskillmovie.utils.ResultStatus
import com.jonrysimbolon.testskillmovie.utils.dialog.CustomDialog
import com.jonrysimbolon.testskillmovie.utils.setImageUrl
import com.jonrysimbolon.testskillmovie.utils.setImageUrlWithRadius
import com.jonrysimbolon.testskillmovie.utils.withDateSimpleFormat
import com.jonrysimbolon.testskillmovie.viewmodel.DetailMovieViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class DetailMovieFragment : Fragment() {

    private val binding by lazy { FragmentDetailMovieBinding.inflate(layoutInflater) }
    private val viewModel: DetailMovieViewModel by activityViewModel()

    private val loadingDialog: CustomDialog by inject()
    private val failureDialog: CustomDialog by inject()
    private val glide: RequestManager by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val bundle = DetailMovieFragmentArgs.fromBundle(arguments as Bundle)
        val movieId = bundle.id
        val movieTitle = bundle.title

        viewModel.getDetailMovie(movieId)
        requireActivity().title = movieTitle
        viewModel.detailMovie.observe(viewLifecycleOwner) { result ->
            when (result) {
                ResultStatus.Loading -> loadingDialog.show()

                is ResultStatus.Error -> {
                    loadingDialog.show(false)
                    failureDialog.show(true)
                }

                is ResultStatus.Success -> {
                    loadingDialog.show(false)
                    whatNext(result.data)
                }
            }
        }
    }

    private fun whatNext(data: DetailMovieModel) {
        binding.apply {
            setImageUrl(
                glide,
                data.backdropPath,
                backgroundIv
            )
            setImageUrlWithRadius(
                glide,
                data.posterPath,
                radius,
                posterIv
            )
            releaseDateTv.text = data.releaseDate.withDateSimpleFormat()
            runTimeTv.text = getString(R.string.run_time_value, data.runtime.toString())
            taglineTv.text = data.tagline
            overViewTv.text = data.overview
            userReviewIv.setOnClickListener {
                val toUserViewFragment = DetailMovieFragmentDirections.actionDetailMovieFragmentToUserReviewFragment(
                    data.id,
                    data.title
                )
                findNavController()
                    .navigate(
                        toUserViewFragment
                    )
            }
            trailerIv.setOnClickListener {
                val toTrailerFragment = DetailMovieFragmentDirections.actionDetailMovieFragmentToTrailerFragment(
                    data.id,
                    data.title
                )
                findNavController()
                    .navigate(
                        toTrailerFragment
                    )
            }
        }
    }

    companion object{
        const val radius: Int = 12
    }
}