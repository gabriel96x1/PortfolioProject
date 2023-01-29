package com.rzs.corroutinesproject.domain.model

import com.google.gson.annotations.SerializedName

data class AuthToken(
    @SerializedName("access_token")
    val token: String?,

    @SerializedName("expires_in")
    val time: Long?,

    @SerializedName("token_type")
    val type: String?
)
