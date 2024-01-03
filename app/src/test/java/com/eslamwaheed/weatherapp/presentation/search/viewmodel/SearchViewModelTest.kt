package com.eslamwaheed.weatherapp.presentation.search.viewmodel

import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.decorator.optional.NeverOptionalStrategy
import com.appmattus.kotlinfixture.decorator.optional.optionalStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.eslamwaheed.domain.entity.search.SearchResponse
import com.eslamwaheed.domain.entity.search.SearchResponseItem
import com.eslamwaheed.domain.usecase.GetSearchDataUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.orbitmvi.orbit.test.test

@RunWith(JUnit4::class)
class SearchViewModelTest {
    private val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
        optionalStrategy(NeverOptionalStrategy)
        repeatCount { 2 }
    }

    @MockK
    private lateinit var getSearchDataUseCase: GetSearchDataUseCase

    private lateinit var classUnderTest: SearchViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        classUnderTest = SearchViewModel(getSearchDataUseCase)
    }

    @Test
    fun `search when getSearchDataUseCase success reduce searchResponse`() =
        runTest {
            val searchResponse: SearchResponse = fixture()

            coEvery { getSearchDataUseCase.invoke("cairo") }.returns(Result.success(searchResponse))

            classUnderTest.test(this) {
                expectInitialState()
                containerHost.search("cairo")
                expectState { copy(isLoading = true) }
                expectState { copy(searchResponse = searchResponse) }
                expectState { copy(isLoading = false) }
            }
        }

    @Test
    fun `search when getSearchDataUseCase failure post side effect ShowError`() =
        runTest {
            coEvery { getSearchDataUseCase.invoke("cairo") }.returns(Result.failure(Throwable("error")))

            classUnderTest.test(this) {
                expectInitialState()
                containerHost.search("cairo")
                expectState { copy(isLoading = true) }
                expectSideEffect(SearchSideEffect.ShowError("error"))
                expectState { copy(isLoading = false) }
            }
        }

    @Test
    fun `onSearchItemClicked then post side effect NavigateBackToHome`() =
        runTest {
            val item: SearchResponseItem = fixture()

            classUnderTest.test(this) {
                expectInitialState()
                containerHost.onSearchItemClicked(item)
                expectSideEffect(SearchSideEffect.NavigateBackToHome(item))
            }
        }
}