package com.jonrysimbolon.testskillmovie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.jonrysimbolon.testskillmovie.adapter.CategoryAdapter
import com.jonrysimbolon.testskillmovie.databinding.FragmentCategoryBinding
import com.jonrysimbolon.testskillmovie.utils.ResultStatus
import com.jonrysimbolon.testskillmovie.utils.dialog.CustomDialog
import com.jonrysimbolon.testskillmovie.viewmodel.CategoryViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class CategoryFragment : Fragment() {

    private val binding: FragmentCategoryBinding by lazy {
        FragmentCategoryBinding.inflate(layoutInflater)
    }

    private val viewModel: CategoryViewModel by activityViewModel()
    private val adapter: CategoryAdapter by inject()
    private val loadingDialog: CustomDialog by inject()
    private val failureDialog: CustomDialog by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            categoryRv.adapter = adapter
            adapter.onClickItem = { view, data ->
                val toDetailFragment =
                    CategoryFragmentDirections.actionCategoryFragmentToMovieFragment(
                        data.id,
                        data.name
                    )
                view.findNavController().navigate(toDetailFragment)
            }
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.category.collect { result ->
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
                                val data = result.data
                                adapter.updateData(data)
                            }
                        }
                    }
                }
            }
        }
    }
}