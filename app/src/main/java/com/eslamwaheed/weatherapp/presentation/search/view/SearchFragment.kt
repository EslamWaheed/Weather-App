package com.eslamwaheed.weatherapp.presentation.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.eslamwaheed.weatherapp.databinding.FragmentSearchBinding
import com.eslamwaheed.weatherapp.presentation.search.viewmodel.SearchSideEffect
import com.eslamwaheed.weatherapp.presentation.search.viewmodel.SearchState
import com.eslamwaheed.weatherapp.presentation.search.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
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

    private fun handelState(state: SearchState) {

    }

    private fun handelSideEffect(sideEffect: SearchSideEffect) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}