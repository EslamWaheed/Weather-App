package com.eslamwaheed.weatherapp.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import com.eslamwaheed.domain.entity.search.SearchResponseItem
import com.eslamwaheed.domain.usecase.GetRealtimeDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val realtimeDataUseCase: GetRealtimeDataUseCase
) : ContainerHost<HomeState, HomeSideEffect>, ViewModel() {

    override val container: Container<HomeState, HomeSideEffect> = container(HomeState())

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

    fun onLastLocationSuccess(latitude: Double?, longitude: Double?) = intent {
        reduce { state.copy(isLoading = true) }
        realtimeDataUseCase.invoke("${latitude},${longitude}").fold(
            {
                reduce {
                    state.copy(realtimeResponse = it)
                }
            },
            {
                postSideEffect(HomeSideEffect.ShowError(it.message))
            }
        )
        reduce { state.copy(isLoading = false) }
    }

    fun onSearchResultBack(item: SearchResponseItem?) = intent {
        onLastLocationSuccess(item?.lat, item?.lon)
    }
}