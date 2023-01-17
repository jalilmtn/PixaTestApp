package com.example.feed

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Resource
import com.example.database.data.ImageDatabaseRepo
import com.example.network.usecase.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import imagedb.ImageEntitiy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase,
    private val imageDatabaseRepo: ImageDatabaseRepo
) : ViewModel() {
    private val _viewState = mutableStateOf(ViewState(emptyFlow()))
    val viewState: State<ViewState> = _viewState
    private var _searchTxt = mutableStateOf("fruits")
    val searchTxt: State<String> = _searchTxt

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
                    it.data?.forEach { image ->
                        imageDatabaseRepo.insertImage(txt, image)
                    }
                    _viewState.value = _viewState.value.copy(isLoading = false)
                }

                is Resource.Error -> {
                    _viewState.value =
                        _viewState.value.copy(
                            error = it.message ?: "An unexpected error occurred",
                            isLoading = false
                        )
                }

                is Resource.Loading -> {
                    _viewState.value = _viewState.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observeSearch() {
        snapshotFlow { _searchTxt.value }
            .onEach {
                if (it.isEmpty())
                    _viewState.value = ViewState(emptyFlow())
            }
            .mapLatest { name ->
                //We don't want to have too many api call
                delay(500)
                if (name.isNotEmpty())
                    searchImage(name)
            }
            .launchIn(viewModelScope)

        snapshotFlow { _searchTxt.value }.map { txt ->
            imageDatabaseRepo.getImagesByTag(txt)
        }.mapLatest {
            _viewState.value = ViewState(it, false)
        }.launchIn(viewModelScope)
    }
}


data class ViewState(
    val imageList: Flow<List<ImageEntitiy>>,
    val isLoading: Boolean = false,
    val error: String = ""

)