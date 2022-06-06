package ru.sigarev.whattowear.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.sigarev.whattowear.domain.usecase.GetLocationsWithCurrentTemperature
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLocationsWithCurrentTemperature: GetLocationsWithCurrentTemperature
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
/*
    private fun fetchData() {
        getLocationsWithCurrentTemperature.fetch()
            .onEach {

            }
            .launchIn(viewModelScope)
    }

 */

    fun processOnClickTab(index: Int) {
        _state.value = _state.value.copy(filter = LocationFilter.values()[index])
    }
}