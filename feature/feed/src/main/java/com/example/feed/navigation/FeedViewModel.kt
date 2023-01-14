package com.example.feed.navigation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Resource
import com.example.domain.usecase.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase
) : ViewModel() {

    private val _nameState = MutableStateFlow(NameState())

    private val _state = mutableStateOf("")
    val state: State<String> = _state
    val state: StateFlow<State> =
        combine(_publicProfile, _nameState, _avatarState) { publicProfile, nameState, avatarState ->

        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            State.Loading
        )
    fun searchImage() {
        getImagesUseCase().onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = it.data ?: ""
                }

                is Resource.Error -> {

                }

                is Resource.Loading -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    private fun observeProfileChange() {
        snapshotFlow { name.value }
            .drop(1)
            .onEach {
                _nameState.value = _nameState.value.copy(
                    nameLoading = true,
                    nameChangeFailed = false,
                )
            }
            .mapLatest { name ->
                delay(1000)
                searchImage()
            }
            .launchIn(viewModelScope)
    }

    data class NameState(
        val nameLoading: Boolean = false,
        val nameChangeFailed: Boolean = false,
    )
}