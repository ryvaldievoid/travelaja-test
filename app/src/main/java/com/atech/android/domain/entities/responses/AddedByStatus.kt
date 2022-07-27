package com.atech.android.domain.entities.responses


import com.google.gson.annotations.SerializedName

data class AddedByStatus(
    @SerializedName("owned")
    val owned: Int?,
    @SerializedName("toplay")
    val toplay: Int?
)