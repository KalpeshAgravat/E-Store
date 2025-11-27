package com.andy.fakestore.feature_auth.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andy.fakestore.core.utils.Resource
import com.andy.fakestore.feature_auth.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AuthState())
    val state: State<AuthState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.Login -> {
                login(event.username, event.password)
            }
        }
    }

    private fun login(username: String, password: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true, error = null)
            val result = loginUseCase(username, password)
            when (result) {
                is Resource.Success -> {
                    _state.value = state.value.copy(isLoading = false, token = result.data)
                    _eventFlow.emit(UiEvent.NavigateToHome)
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(isLoading = false, error = result.message)
                    _eventFlow.emit(UiEvent.ShowSnackbar(result.message ?: "Unknown error"))
                }
                is Resource.Loading -> {
                    _state.value = state.value.copy(isLoading = true)
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object NavigateToHome : UiEvent()
    }
}
