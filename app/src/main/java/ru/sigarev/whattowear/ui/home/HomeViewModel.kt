package ru.sigarev.whattowear.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.sigarev.whattowear.domain.usecase.GetLocationsWithCurrentTemperature
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLocationsWithCurrentTemperature: GetLocationsWithCurrentTemperature
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        _state.value = _state.value.copy(loading = true)
        combine(_state, getLocationsWithCurrentTemperature.fetch()) { state, locations ->
            locations.filter {
                state.filter == LocationFilter.All ||
                        (it.location.isFavorite && state.filter == LocationFilter.Favorites)
            }
        }
            .onEach { locations ->
                _state.value = _state.value.copy(
                    loading = false,
                    locationsWithTemperature = locations
                )
                locations.forEach {
                    Log.d("SA", "$it")
                }
            }
            .catch { cause ->
                Log.e(TAG, "cause flow", cause)
            }
            .launchIn(viewModelScope)
    }

    fun processOnClickTab(index: Int) {
        _state.value = _state.value.copy(filter = LocationFilter.values()[index])
    }

    fun processOnClickFavorite(uid: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            getLocationsWithCurrentTemperature.changeFavorite(uid, isFavorite)
        }
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}