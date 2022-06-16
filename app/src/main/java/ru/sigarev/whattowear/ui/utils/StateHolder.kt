package ru.sigarev.whattowear.ui.utils

import kotlinx.coroutines.flow.StateFlow

internal abstract class StateHolder<T> {
    protected abstract val state: StateFlow<T>
    protected fun transition(map: T.() -> T): T {
        return map(state.value)
    }

    protected abstract fun reduce(newState: T)
}