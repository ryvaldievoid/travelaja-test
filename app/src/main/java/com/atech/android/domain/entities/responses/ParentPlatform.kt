package com.atech.android.domain.entities.responses


import com.google.gson.annotations.SerializedName

data class ParentPlatform(
    @SerializedName("platform")
    val platform: Platform?
)