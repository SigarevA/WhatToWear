package ru.sigarev.whattowear.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.sigarev.whattowear.domain.models.LocationWithTemperature
import ru.sigarev.whattowear.domain.usecase.GetLocationsWithCurrentTemperature
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLocationsWithCurrentTemperature: GetLocationsWithCurrentTemperature
) : ViewModel() {
    private var _dataJob: Job? = null
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        fetchData()
    }

    fun fetchData() {
        newState { copy(loading = true) }
        _dataJob = data.onEach { locations ->
            newState {
                copy(
                    loading = false,
                    locationsWithTemperature = locations
                )
            }
        }
            .catch { cause ->
                newState { copy(loading = false, exception = cause) }
                Log.e(TAG, "cause flow", cause)
            }
            .launchIn(viewModelScope)
    }

    fun processOnRefresh() {
        _dataJob?.let { job ->
            if (job.isActive)
                job.cancel()
        }
        newState { copy(isRefreshing = true) }
        _dataJob = data
            .onEach { locations ->
                _state.value = _state.value.copy(
                    isRefreshing = false,
                    locationsWithTemperature = locations
                )
            }.catch { cause ->
                Log.e(TAG, "cause flow", cause)
            }
            .launchIn(viewModelScope)
    }

    private val data: Flow<List<LocationWithTemperature>>
        get() = combine(_state, getLocationsWithCurrentTemperature.fetch()) { state, locations ->
            locations.filter {
                state.filter == LocationFilter.All ||
                        (it.location.isFavorite && state.filter == LocationFilter.Favorites)
            }
        }

    fun processOnClickTab(index: Int) {
        _state.value = _state.value.copy(filter = LocationFilter.values()[index])
    }

    fun processOnClickFavorite(uid: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            getLocationsWithCurrentTemperature.changeFavorite(uid, isFavorite)
        }
    }

    private fun newState(reduce: HomeState.() -> HomeState) {
        _state.value = _state.value.reduce()
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}