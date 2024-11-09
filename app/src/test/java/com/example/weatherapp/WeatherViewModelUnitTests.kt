package com.example.weatherapp

import app.cash.turbine.test
import com.example.weatherapp.data.model.AdministrativeArea
import com.example.weatherapp.data.model.Country
import com.example.weatherapp.data.model.Location
import com.example.weatherapp.domain.HintHistoryRepository
import com.example.weatherapp.domain.ModelMapper
import com.example.weatherapp.domain.WeatherRepository
import com.example.weatherapp.domain.model.CurrentConditions
import com.example.weatherapp.domain.model.DailyForecast
import com.example.weatherapp.domain.model.Hint
import com.example.weatherapp.domain.model.HourlyWeatherForecasts
import com.example.weatherapp.ui.WeatherViewModel
import io.mockk.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelUnitTests {

    private val weatherRepository: WeatherRepository = mockk()
    private val hintHistoryRepository: HintHistoryRepository = mockk()
    private val modelMapper: ModelMapper = mockk()

    private lateinit var viewModel: WeatherViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = WeatherViewModel(weatherRepository, modelMapper, hintHistoryRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchLocations updates hints based on query`() = runTest {
        // given
        val query = "Warsaw"
        val historyHints = listOf(Hint("123456", "Paris", true))
        val mockLocations = getMockLocations()
        val mappedHint = Hint("12345","Warsaw, Poland")

        coEvery { hintHistoryRepository.getHints() } returns flowOf(historyHints)
        coEvery { weatherRepository.fetchLocations(query) } returns Result.success(
            mockLocations
        )
        every { modelMapper.toHint(any()) } returns mappedHint

        // when
        viewModel.fetchLocations(query)

        // then
        viewModel.hints.test {
            awaitItem()
            assertEquals(historyHints + listOf(mappedHint), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `fetchLocations should filter out historical hint if is the same as query`() = runTest {
        // given
        val query = "Paris"
        val historyHints = listOf(Hint("123456", "Paris", true))
        val mockLocations = getMockLocations()
        val mappedHint = Hint("12345","Warsaw, Poland")

        coEvery { hintHistoryRepository.getHints() } returns flowOf(historyHints)
        coEvery { weatherRepository.fetchLocations(query) } returns Result.success(
            mockLocations
        )
        every { modelMapper.toHint(any()) } returns mappedHint

        // when
        viewModel.fetchLocations(query)

        // then
        viewModel.hints.test {
            awaitItem()
            assertEquals(listOf(mappedHint), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onHintSelected should update selected location and save hint`() = runTest {
        // given
        val hint = Hint("12345", "Warsaw", false)
        val updatedHint = Hint("12345", "Warsaw", true)
        coEvery { hintHistoryRepository.saveHint(any()) } just Runs

        // when
        viewModel.onHintSelected(hint)

        // then
        advanceUntilIdle()
        assertEquals(viewModel.locationIsSelected.value, true)
        coVerify { hintHistoryRepository.saveHint(updatedHint) }
    }

    @Test
    fun `fetchDetails calls fetchCurrentConditions, fetchHourlyForecasts, and getWeekForecast`() = runTest {
        // given
        val currentConditions = mockk<CurrentConditions>()
        val weatherForecast = mockk<HourlyWeatherForecasts>()
        val dailyForecast = mockk<DailyForecast>()

        coEvery { weatherRepository.fetchCurrentConditions(any()) } returns Result.success(listOf(mockk()))
        coEvery { weatherRepository.fetchHourlyForecasts(any()) } returns Result.success(mockk())
        coEvery { weatherRepository.fetchDailyForecasts(any()) } returns Result.success(mockk())
        coEvery { hintHistoryRepository.saveHint(any()) } just Runs

        every { modelMapper.toCurrentConditions(any()) } returns currentConditions
        every { modelMapper.toWeatherForecast(any()) } returns weatherForecast
        every { modelMapper.toWeekForecast(any()) } returns listOf(dailyForecast)

        viewModel.onHintSelected(Hint("12345", "Warsaw", false))
        advanceUntilIdle()

        // when
        viewModel.fetchDetails()

        // then
        advanceUntilIdle()
        coVerify { weatherRepository.fetchCurrentConditions(any()) }
        coVerify { weatherRepository.fetchHourlyForecasts(any()) }
        coVerify { weatherRepository.fetchDailyForecasts(any()) }
    }

    @Test
    fun `fetchCurrentConditions updates currentConditions`() = runTest {
        // given
        val hint = Hint("Warsaw", "Poland", false)
        val currentConditions = mockk<CurrentConditions>()
        val weatherForecast = mockk<HourlyWeatherForecasts>()
        val dailyForecast = mockk<DailyForecast>()

        coEvery { weatherRepository.fetchCurrentConditions(any()) } returns Result.success(listOf(mockk()))
        coEvery { weatherRepository.fetchHourlyForecasts(any()) } returns Result.success(mockk())
        coEvery { weatherRepository.fetchDailyForecasts(any()) } returns Result.success(mockk())
        coEvery { hintHistoryRepository.saveHint(any()) } just Runs

        every { modelMapper.toCurrentConditions(any()) } returns currentConditions
        every { modelMapper.toWeatherForecast(any()) } returns weatherForecast
        every { modelMapper.toWeekForecast(any()) } returns listOf(dailyForecast)

        viewModel.onHintSelected(hint)

        // when
        viewModel.fetchDetails()

        // then
        viewModel.currentConditions.test {
            awaitItem()
            assertEquals(currentConditions, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `fetchHourlyForecasts updates hourlyForecast`() = runTest {
        // given
        val hint = Hint("Warsaw", "Poland", false)
        val currentConditions = mockk<CurrentConditions>()
        val weatherForecast = mockk<HourlyWeatherForecasts>()
        val dailyForecast = mockk<DailyForecast>()

        coEvery { weatherRepository.fetchCurrentConditions(any()) } returns Result.success(listOf(mockk()))
        coEvery { weatherRepository.fetchHourlyForecasts(any()) } returns Result.success(mockk())
        coEvery { weatherRepository.fetchDailyForecasts(any()) } returns Result.success(mockk())
        coEvery { hintHistoryRepository.saveHint(any()) } just Runs

        every { modelMapper.toCurrentConditions(any()) } returns currentConditions
        every { modelMapper.toWeatherForecast(any()) } returns weatherForecast
        every { modelMapper.toWeekForecast(any()) } returns listOf(dailyForecast)

        viewModel.onHintSelected(hint)

        // when
        viewModel.fetchDetails()

        // then
        viewModel.hourlyForecast.test {
            awaitItem()
            assertEquals(weatherForecast, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getWeekForecast updates dailyForecast`() = runTest {
        // given
        val hint = Hint("Warsaw", "Poland", false)
        val currentConditions = mockk<CurrentConditions>()
        val weatherForecast = mockk<HourlyWeatherForecasts>()
        val dailyForecast = mockk<DailyForecast>()

        coEvery { weatherRepository.fetchCurrentConditions(any()) } returns Result.success(listOf(mockk()))
        coEvery { weatherRepository.fetchHourlyForecasts(any()) } returns Result.success(mockk())
        coEvery { weatherRepository.fetchDailyForecasts(any()) } returns Result.success(mockk())
        coEvery { hintHistoryRepository.saveHint(any()) } just Runs

        every { modelMapper.toCurrentConditions(any()) } returns currentConditions
        every { modelMapper.toWeatherForecast(any()) } returns weatherForecast
        every { modelMapper.toWeekForecast(any()) } returns listOf(dailyForecast)

        viewModel.onHintSelected(hint)

        // when
        viewModel.fetchDetails()

        // then
        viewModel.dailyForecast.test {
            awaitItem()
            assertEquals(listOf(dailyForecast), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    private fun getMockLocations() = listOf(
        Location(
            version = 1,
            key = "12345",
            type = "City",
            rank = 10,
            localizedName = "Warsaw",
            country = Country(
                id = "PL",
                localizedName = "Poland"
            ),
            administrativeArea = AdministrativeArea(
                id = "MZ",
                localizedName = "Mazowieckie"
            )
        )
    )
}
