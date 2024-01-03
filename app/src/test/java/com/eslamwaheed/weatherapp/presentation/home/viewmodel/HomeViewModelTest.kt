package com.eslamwaheed.weatherapp.presentation.home.viewmodel

import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.decorator.optional.NeverOptionalStrategy
import com.appmattus.kotlinfixture.decorator.optional.optionalStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.eslamwaheed.domain.entity.realtime.RealtimeResponse
import com.eslamwaheed.domain.entity.search.SearchResponseItem
import com.eslamwaheed.domain.usecase.GetRealtimeDataUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.any
import org.orbitmvi.orbit.test.test

@RunWith(JUnit4::class)
class HomeViewModelTest {
    private val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
        optionalStrategy(NeverOptionalStrategy)
        repeatCount { 2 }
    }

    @MockK
    private lateinit var realtimeDataUseCase: GetRealtimeDataUseCase

    private lateinit var classUnderTest: HomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        classUnderTest = HomeViewModel(realtimeDataUseCase)
    }

    @Test
    fun `checkLocationPermission when value equal true then post side effect PermissionGranted`() =
        runTest {
            classUnderTest.test(this) {
                expectInitialState()
                containerHost.checkLocationPermission(true)
                expectSideEffect(HomeSideEffect.PermissionGranted)
            }
        }

    @Test
    fun `checkLocationPermission when value equal false then post side effect PermissionDenied`() =
        runTest {
            classUnderTest.test(this) {
                expectInitialState()
                containerHost.checkLocationPermission(false)
                expectSideEffect(HomeSideEffect.PermissionDenied)
            }
        }

    @Test
    fun `onSearchClicked invoked then post side effect`() = runTest {
        classUnderTest.test(this) {
            expectInitialState()
            containerHost.onSearchClicked()
            expectSideEffect(HomeSideEffect.NavigateToSearch)
        }
    }

    @Test
    fun `onLastLocationSuccess when realtimeDataUseCase success reduce realtimeResponse`() =
        runTest {
            val realtimeResponse: RealtimeResponse = fixture()

            coEvery { realtimeDataUseCase.invoke(any()) }.returns(Result.success(realtimeResponse))

            classUnderTest.test(this) {
                expectInitialState()
                containerHost.onLastLocationSuccess(any(), any())
                expectState { copy(isLoading = true) }
                expectState { copy(realtimeResponse = realtimeResponse) }
                expectState { copy(isLoading = false) }
            }
        }

    @Test
    fun `onLastLocationSuccess when realtimeDataUseCase failure post side effect ShowError`() =
        runTest {
            coEvery { realtimeDataUseCase.invoke(any()) }.returns(Result.failure(Throwable("error")))

            classUnderTest.test(this) {
                expectInitialState()
                containerHost.onLastLocationSuccess(any(), any())
                expectState { copy(isLoading = true) }
                expectSideEffect(HomeSideEffect.ShowError("error"))
                expectState { copy(isLoading = false) }
            }
        }

    @Test
    fun `onSearchResultBack when value not equal null and realtime success then reduce realtimeResponse`() =
        runTest {
            val realtimeResponse: RealtimeResponse = fixture()

            coEvery { realtimeDataUseCase.invoke(any()) }.returns(Result.success(realtimeResponse))

            val searchResponseItem: SearchResponseItem = fixture()
            classUnderTest.test(this) {
                expectInitialState()
                containerHost.onSearchResultBack(searchResponseItem)
                expectState { copy(isLoading = true) }
                expectState { copy(realtimeResponse = realtimeResponse) }
                expectState { copy(isLoading = false) }
            }
        }
}