package com.example.gifsapp.gifsItem

data class GifModelDomain(
    val data: List<Data>
)

data class Data(
    val id: String,
    val images: Images
)

data class Images(
    val original: Original
)

data class Original(
    val url: String
)