package com.rzs.corroutinesproject.domain.model

import com.google.gson.annotations.SerializedName

data class Game(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("summary")
    val summary: String?,

    @SerializedName("url")
    val url: String?,
)