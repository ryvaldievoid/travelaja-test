package com.atech.android.domain.entities.responses


import com.google.gson.annotations.SerializedName

data class Store(
    @SerializedName("store")
    val store: StoreX?
)