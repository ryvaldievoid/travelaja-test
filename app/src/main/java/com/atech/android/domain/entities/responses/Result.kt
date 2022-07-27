package com.atech.android.domain.entities.responses


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("added")
    val added: Int?,
    @SerializedName("added_by_status")
    val addedByStatus: AddedByStatus?,
    @SerializedName("background_image")
    val backgroundImage: String?,
    @SerializedName("clip")
    val clip: Any?,
    @SerializedName("community_rating")
    val communityRating: Int?,
    @SerializedName("dominant_color")
    val dominantColor: String?,
    @SerializedName("esrb_rating")
    val esrbRating: EsrbRating?,
    @SerializedName("genres")
    val genres: List<Genre>?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("metacritic")
    val metacritic: Any?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("parent_platforms")
    val parentPlatforms: List<ParentPlatform>?,
    @SerializedName("platforms")
    val platforms: List<PlatformX>?,
    @SerializedName("playtime")
    val playtime: Int?,
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("rating_top")
    val ratingTop: Int?,
    @SerializedName("ratings")
    val ratings: List<Any>?,
    @SerializedName("ratings_count")
    val ratingsCount: Int?,
    @SerializedName("released")
    val released: String?,
    @SerializedName("reviews_count")
    val reviewsCount: Int?,
    @SerializedName("reviews_text_count")
    val reviewsTextCount: Int?,
    @SerializedName("saturated_color")
    val saturatedColor: String?,
    @SerializedName("score")
    val score: Any?,
    @SerializedName("short_screenshots")
    val shortScreenshots: List<ShortScreenshot>?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("stores")
    val stores: List<Store>?,
    @SerializedName("suggestions_count")
    val suggestionsCount: Int?,
    @SerializedName("tags")
    val tags: List<Tag>?,
    @SerializedName("tba")
    val tba: Boolean?,
    @SerializedName("updated")
    val updated: String?,
    @SerializedName("user_game")
    val userGame: Any?
)