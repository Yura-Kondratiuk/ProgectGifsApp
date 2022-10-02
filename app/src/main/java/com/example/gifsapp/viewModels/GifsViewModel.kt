package com.example.gifsapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gifsapp.data.repository.GifsRepository
import com.example.gifsapp.mappers.GifMapper.toGifModel
import com.example.gifsapp.model.GifModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GifsViewModel(
    private val repository: GifsRepository
) : ViewModel() {

    private val _gifs = MutableStateFlow<List<GifModel>>(listOf())
    val gifs: StateFlow<List<GifModel>> = _gifs

    private var removedIds: MutableList<String> = mutableListOf()

    init {
        getRemovedIds()
    }

    fun getGifs(query: String = "") {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                if (query.isEmpty()) {
                    repository.getRandomGifs()
                } else repository.getGifs(query)
            }
            val gifs = result.data.filter {
                removedIds.contains(it.id).not()
            }.map {
                it.toGifModel()
            }
            _gifs.value = gifs
        }
    }

     fun saveRemovedId(id: String) {
         viewModelScope.launch {
             repository.saveRemovedId(id)
             removedIds.add(id)
         }
     }

     private fun getRemovedIds() {
         viewModelScope.launch {
             val result = withContext(Dispatchers.IO) {
                 repository.getRemovedIds()
             }
             removedIds = result.toMutableList()
         }
     }
}