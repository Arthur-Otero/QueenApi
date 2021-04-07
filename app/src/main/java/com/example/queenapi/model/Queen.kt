package com.example.queenapi.model


import com.google.gson.annotations.SerializedName

data class Queen(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("missCongeniality")
    val missCongeniality: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("quote")
    val quote: String?,
    @SerializedName("winner")
    val winner: Boolean?
)