package com.example.feed

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Resource
import com.example.database.data.ImageRepo
import com.example.domain.usecase.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import imagedb.ImageEntitiy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase,
    private val imageRepo: ImageRepo
) : ViewModel() {
    private val _state = mutableStateOf(State(emptyList()))
    val state: androidx.compose.runtime.State<State> = _state
    private var _searchTxt = mutableStateOf("fruits")
    val searchTxt: androidx.compose.runtime.State<String> = _searchTxt

    init {
        observeSearch()
    }

    fun setName(txt: String) {
        _searchTxt.value = txt
    }

    private fun searchImage(txt: String) {
        getImagesUseCase(txt).onEach {
            when (it) {
                is Resource.Success -> {
                }

                is Resource.Error -> {
                    _state.value =
                        State(emptyList(), error = it.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

        viewModelScope.launch {
            imageRepo.getImages().collectLatest {
                _state.value = State(it.filter { it.tags.contains(_searchTxt.value, false) })
            }
        }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observeSearch() {
        snapshotFlow { _searchTxt.value }
            .onEach {
                if (it.isEmpty())
                    _state.value = State(emptyList())
            }
            .mapLatest { name ->
                //We don't want to have too many api call
                delay(1000)
                if (name.isNotEmpty())
                    searchImage(name)
            }
            .launchIn(viewModelScope)
    }


    data class State(
        val imageList: List<ImageEntitiy>,
        val isLoading: Boolean = false,
        val error: String = ""

    )

}