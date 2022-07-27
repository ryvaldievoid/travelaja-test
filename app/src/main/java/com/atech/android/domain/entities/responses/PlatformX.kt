package com.atech.android.domain.entities.responses


import com.google.gson.annotations.SerializedName

data class PlatformX(
    @SerializedName("platform")
    val platform: Platform?
)