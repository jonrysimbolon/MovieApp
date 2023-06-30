package com.jonrysimbolon.testskillmovie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.jonrysimbolon.testskillmovie.R
import com.jonrysimbolon.testskillmovie.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private val binding by lazy { FragmentSplashBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.splashImage.startAnimation(
            AnimationUtils.loadAnimation(requireContext(), animateSplashImage)
        )

        view.let {
            it.postDelayed({
                it.findNavController().navigate(toCategoryFragment)
            }, delayMillis)
        }
    }

    companion object {
        @AnimRes
        private val animateSplashImage = R.anim.side_slide
        private val toCategoryFragment = R.id.action_splashFragment_to_categoryFragment
        private const val delayMillis: Long = 2000L
    }
}