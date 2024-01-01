package com.eslamwaheed.weatherapp.presentation.search.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ContainerHost<SearchState, SearchSideEffect>,
    ViewModel() {

    override val container: Container<SearchState, SearchSideEffect> = container(SearchState())

}