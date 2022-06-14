package ru.sigarev.whattowear.ui.setting_location_name

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.sigarev.whattowear.domain.usecase.CreateLocationUseCase
import javax.inject.Inject

@HiltViewModel
class SettingsLocationNameViewModel @Inject constructor(
    private val createLocationUseCase: CreateLocationUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(SettingsLocationNameState())
    val state = _state.asStateFlow()

    fun processNameChange(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    fun processOnClickSave() {
        viewModelScope.launch {
            createLocationUseCase.save(state.value.name)
        }
    }
}