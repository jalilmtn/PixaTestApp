package com.example.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.database.data.ImageDatabaseRepo
import com.example.detail.navigation.imageIdArg
import dagger.hilt.android.lifecycle.HiltViewModel
import imagedb.ImageEntitiy
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repo: ImageDatabaseRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val imageId = checkNotNull(savedStateHandle[imageIdArg])

    private val _state = mutableStateOf<ImageEntitiy?>(null)
    var viewState: State<ImageEntitiy?> = _state

    init {
        getImageById(imageId as Long)
    }

    private fun getImageById(imageId: Long) {
        viewModelScope.launch {
            _state.value = repo.getImageById(imageId)
        }

    }
}