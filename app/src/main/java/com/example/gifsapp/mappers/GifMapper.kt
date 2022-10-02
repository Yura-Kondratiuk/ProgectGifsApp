package com.example.gifsapp.mappers

import com.example.gifsapp.gifsItem.Data
import com.example.gifsapp.model.GifModel

object GifMapper {

    fun Data.toGifModel():GifModel {
        return GifModel(
            url = images.original.url,
            id = id
        )
    }
}