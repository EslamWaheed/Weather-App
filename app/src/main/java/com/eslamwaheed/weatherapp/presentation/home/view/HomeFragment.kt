package com.eslamwaheed.weatherapp.presentation.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.eslamwaheed.weatherapp.databinding.FragmentHomeBinding
import com.eslamwaheed.weatherapp.presentation.home.viewmodel.HomeSideEffect
import com.eslamwaheed.weatherapp.presentation.home.viewmodel.HomeState
import com.eslamwaheed.weatherapp.presentation.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observe(
            lifecycleOwner = viewLifecycleOwner,
            state = ::handelState,
            sideEffect = ::handelSideEffect
        )
    }

    private fun handelState(state: HomeState) {

    }

    private fun handelSideEffect(sideEffect: HomeSideEffect) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}