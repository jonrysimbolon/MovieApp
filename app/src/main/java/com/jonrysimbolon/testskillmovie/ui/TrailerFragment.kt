package com.jonrysimbolon.testskillmovie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jonrysimbolon.testskillmovie.adapter.TrailerAdapter
import com.jonrysimbolon.testskillmovie.data.remote.model.VideoModel
import com.jonrysimbolon.testskillmovie.databinding.FragmentTrailerBinding
import com.jonrysimbolon.testskillmovie.utils.ResultStatus
import com.jonrysimbolon.testskillmovie.utils.dialog.CustomDialog
import com.jonrysimbolon.testskillmovie.viewmodel.TrailerViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class TrailerFragment : Fragment() {

    private val binding by lazy { FragmentTrailerBinding.inflate(layoutInflater) }
    private val viewModel: TrailerViewModel by activityViewModel()

    private val adapter: TrailerAdapter by inject()
    private val loadingDialog: CustomDialog by inject()
    private val failureDialog: CustomDialog by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bundle = TrailerFragmentArgs.fromBundle(arguments as Bundle)
        val movieId = bundle.id
        val movieTitle = bundle.title

        requireActivity().title = movieTitle
        viewModel.getAllVideos(movieId)
        viewModel.trailer.observe(viewLifecycleOwner) { result ->
            when (result) {
                ResultStatus.Loading -> {
                    loadingDialog.show(true)
                }

                is ResultStatus.Error -> {
                    loadingDialog.show(false)
                    failureDialog.show(true)
                }

                is ResultStatus.Success -> {
                    loadingDialog.show(false)
                    showCategoriesUi(result.data)
                }
            }
        }

    }

    private fun showCategoriesUi(data: List<VideoModel>) {
        binding.apply {
            trailerRv.adapter = adapter
            adapter.updateData(data)
        }
    }

}