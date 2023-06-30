package com.jonrysimbolon.testskillmovie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.paging.LoadState
import com.jonrysimbolon.testskillmovie.adapter.FooterLoadingStateAdapter
import com.jonrysimbolon.testskillmovie.adapter.UserReviewAdapter
import com.jonrysimbolon.testskillmovie.databinding.FragmentUserReviewBinding
import com.jonrysimbolon.testskillmovie.utils.dialog.CustomDialog
import com.jonrysimbolon.testskillmovie.viewmodel.UserReviewViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class UserReviewFragment : Fragment() {

    private val binding by lazy { FragmentUserReviewBinding.inflate(layoutInflater) }
    private val viewModel: UserReviewViewModel by activityViewModel()

    private val adapter: UserReviewAdapter by inject()
    private val loadingDialog: CustomDialog by inject()
    private val failureDialog: CustomDialog by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bundle = UserReviewFragmentArgs.fromBundle(arguments as Bundle)
        val movieId = bundle.id
        val movieTitle = bundle.title

        requireActivity().title = movieTitle
        viewModel.getAllReviews(movieId)

        viewModel.userReviewLivePaging.observe(viewLifecycleOwner) { review ->
            review?.let {
                adapter.submitData(lifecycle, it)
            }
        }

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

        binding.rvReview.adapter = adapter.withLoadStateFooter(
            footer = FooterLoadingStateAdapter {
                adapter.retry()
            }
        )
    }
}