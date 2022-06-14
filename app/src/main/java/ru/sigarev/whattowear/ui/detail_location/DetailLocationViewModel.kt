package ru.sigarev.whattowear.ui.detail_location

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.sigarev.whattowear.domain.usecase.DetailLocationUseCase
import ru.sigarev.whattowear.ui.destinations.DetailLocationScreenDestination
import javax.inject.Inject

@HiltViewModel
class DetailLocationViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val detailLocationUseCase: DetailLocationUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<DetailLocationState>

    init {
        val args = DetailLocationScreenDestination.argsFrom(stateHandle)
        _state = MutableStateFlow(DetailLocationState(args.id.toInt()))
        initLoading()
    }

    val state = _state.asStateFlow()

    private fun initLoading() {
        viewModelScope.launch {
            Log.d(TAG, "initLoading : start")
            newState { copy(isLoading = true) }
            try {
                val locationWeather =
                    detailLocationUseCase.fetchLocationWithWeather(_state.value.uid)
                newState { copy(isLoading = false, content = locationWeather) }
                Log.d(TAG, "initLoading : success")
            } catch (ex: Exception) {
                Log.e(TAG, "initLoading : error ", ex)
                newState { copy(isLoading = false, exception = ex) }
            }
        }
    }

    fun processRefresh() {
        if (!_state.value.isLoading)
            viewModelScope.launch {
                newState { copy(isRefresh = true) }
                try {
                    val locationWeather =
                        detailLocationUseCase.fetchLocationWithWeather(state.value.uid)
                    newState { copy(isRefresh = false, content = locationWeather) }
                } catch (ex: Exception) {
                    newState { copy(isRefresh = false, exception = ex) }
                }
            }
    }

    fun processOnClickDelete() {
        newState { copy(isDeletion = true) }
    }

    fun processDismissDeletion() {
        _state.value = _state.value.copy(isDeletion = false)
    }

    private fun newState(map: DetailLocationState.() -> DetailLocationState) {
        _state.value = map(_state.value)
    }

    companion object {
        private const val TAG = "DetailLocationViewModel"
    }
}