package com.example.feed

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Resource
import com.example.domain.model.Image
import com.example.domain.usecase.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase
) : ViewModel() {
    private val _state = mutableStateOf(State(emptyList()))
    val state: androidx.compose.runtime.State<State> = _state

    init {
        searchImage()
    }

    private fun searchImage() {
        getImagesUseCase().onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = State(it.data ?: emptyList())
                }

                is Resource.Error -> {

                    _state.value =
                        State(emptyList(), error = it.message ?: "An unexpected error occured")
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    data class State(
        val imageList: List<Image>,
        val isLoading: Boolean = false,
        val error: String = ""

    )

}