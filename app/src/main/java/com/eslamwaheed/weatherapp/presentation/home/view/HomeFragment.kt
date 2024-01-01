package com.eslamwaheed.weatherapp.presentation.home.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.eslamwaheed.weatherapp.databinding.FragmentHomeBinding
import com.eslamwaheed.weatherapp.presentation.home.viewmodel.HomeSideEffect
import com.eslamwaheed.weatherapp.presentation.home.viewmodel.HomeState
import com.eslamwaheed.weatherapp.presentation.home.viewmodel.HomeViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            viewModel.checkLocationPermission(it)
        }
    }

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
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        checkPermission()
    }

    private fun handelState(state: HomeState) {

    }

    private fun handelSideEffect(sideEffect: HomeSideEffect) {
        when (sideEffect) {
            HomeSideEffect.PermissionDenied -> Unit
            HomeSideEffect.PermissionGranted -> {
                addLastLocationListener()
            }
        }
    }

    private fun checkPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                viewModel.checkLocationPermission(true)
            }

            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION) -> {
                viewModel.checkLocationPermission(false)
            }

            else -> {
                locationPermissionRequest.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
            }
        }
    }

    private fun addLastLocationListener() {
        fusedLocationClient.lastLocation.addOnSuccessListener {
            Toast.makeText(
                requireContext(),
                "${it.latitude} ${it.longitude}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}