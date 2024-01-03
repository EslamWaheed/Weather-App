package com.eslamwaheed.weatherapp.presentation.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslamwaheed.domain.entity.search.SearchResponseItem
import com.eslamwaheed.domain.usecase.GetSearchDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchDataUseCase: GetSearchDataUseCase
) : ContainerHost<SearchState, SearchSideEffect>, ViewModel() {

    private val inputFlow: MutableStateFlow<String> = MutableStateFlow("")

    override val container: Container<SearchState, SearchSideEffect> = container(SearchState()) {
        inputFlow.debounce(500)
            .filterNot { userInput -> userInput.isEmpty() }
            .distinctUntilChanged()
            .flowOn(Dispatchers.IO)
            .onEach { search(it) }
            .launchIn(viewModelScope)
    }

    private fun search(q: String) = intent {
        reduce { state.copy(isLoading = true) }
        getSearchDataUseCase.invoke(q).fold(
            {
                reduce { state.copy(searchResponse = it) }
            },
            {
                postSideEffect(SearchSideEffect.ShowError(it.message))
            }
        )
        reduce { state.copy(isLoading = false) }
    }

    fun onSearchTextChanged(searchText: String) = intent {
        inputFlow.emit(searchText)
    }

    fun onSearchItemClicked(item: SearchResponseItem) = intent {

    }
}