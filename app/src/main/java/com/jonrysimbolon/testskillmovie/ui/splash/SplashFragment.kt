package com.jonrysimbolon.testskillmovie.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.jonrysimbolon.testskillmovie.R
import com.jonrysimbolon.testskillmovie.databinding.FragmentSplashBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class SplashFragment : Fragment() {

    private val binding by lazy { FragmentSplashBinding.inflate(layoutInflater) }
    private val viewModel: SplashViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.splashImage.startAnimation(
            AnimationUtils.loadAnimation(requireContext(), animateSplashImage)
        )

        viewLifecycleOwner.lifecycleScope.launch {
            val isCategoriesLoaded = viewModel.getAllCategories()
            if (isCategoriesLoaded) {
                view.findNavController().navigate(
                    toHomeFragment
                )
            }
        }
    }

    companion object {
        @AnimRes
        private val animateSplashImage = R.anim.side_slide
        private val toHomeFragment = R.id.action_splashFragment_to_homeFragment
    }
}