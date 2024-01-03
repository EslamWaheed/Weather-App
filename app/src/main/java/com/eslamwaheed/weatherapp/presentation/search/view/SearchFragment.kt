package com.eslamwaheed.weatherapp.presentation.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.eslamwaheed.weatherapp.databinding.FragmentSearchBinding
import com.eslamwaheed.weatherapp.presentation.search.adapter.SearchLocationsAdapter
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

    private val searchLocationsAdapter: SearchLocationsAdapter by lazy { SearchLocationsAdapter() }

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
        setUpView()
        setUpListeners()
    }

    private fun setUpView() {
        with(binding) {
            rvLocations.adapter = searchLocationsAdapter
        }
    }

    private fun setUpListeners() {
        with(binding) {
            tieSearch.doAfterTextChanged {
                viewModel.onSearchTextChanged(it.toString())
            }
            searchLocationsAdapter.setItemClick {
                viewModel.onSearchItemClicked(it)
            }
        }
    }

    private fun handelState(state: SearchState) {
        with(state) {
            searchLocationsAdapter.submitList(searchResponse)
        }
    }

    private fun handelSideEffect(sideEffect: SearchSideEffect) {
        when (sideEffect) {
            is SearchSideEffect.ShowError -> {
                Toast.makeText(requireContext(), sideEffect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}