package com.example.tututest.model

import com.squareup.moshi.Json

data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: String,
    val thumbnailExt: String,
    val modified: String,
    @Json(name = "resourceURI") val img: String
)