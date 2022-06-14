package ru.sigarev.whattowear.ui.geolocation_selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.sigarev.whattowear.domain.usecase.CreateLocationUseCase
import javax.inject.Inject

@HiltViewModel
class GeolocationSelectionViewModel @Inject constructor(
    private val createLocationUseCase: CreateLocationUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(GeolocationSelectionState())
    val state = _state.asStateFlow()

    fun processOnTapMap(lat: Double, lon: Double) {
        viewModelScope.launch {
            createLocationUseCase.setLocation(lat, lon)
            _state.value = _state.value.copy(isOpenNextStep = true)
        }
    }

    fun clearAction() {
        _state.value = _state.value.copy(isOpenNextStep = false)
    }
}