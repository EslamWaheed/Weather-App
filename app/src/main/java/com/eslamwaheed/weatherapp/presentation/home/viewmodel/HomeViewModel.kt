package com.eslamwaheed.weatherapp.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import com.eslamwaheed.domain.usecase.GetRealtimeDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val realtimeDataUseCase: GetRealtimeDataUseCase
) : ContainerHost<HomeState, HomeSideEffect>, ViewModel() {

    override val container: Container<HomeState, HomeSideEffect> = container(HomeState()) {
        getRealtimeData("cairo")
    }

    fun checkLocationPermission(it: Boolean) = intent {
        if (it) {
            postSideEffect(HomeSideEffect.PermissionGranted)
        } else {
            postSideEffect(HomeSideEffect.PermissionDenied)
        }
    }

    fun onSearchClicked() = intent {
        postSideEffect(HomeSideEffect.NavigateToSearch)
    }

    private fun getRealtimeData(q: String) = intent {
        realtimeDataUseCase.invoke(q).fold(
            {

            },
            {

            }
        )
    }
}