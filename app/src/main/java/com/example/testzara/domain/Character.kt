package com.example.testzara.domain

import com.google.gson.annotations.SerializedName

class Character {

    @SerializedName("id")
    var id: Int? = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("status")
    var status: String? = null

    @SerializedName("species")
    var species: String? = null

    @SerializedName("location")
    var location = Location()

    @SerializedName("image")
    var image: String? = null

    @SerializedName("episode")
    var episode = ArrayList<String>()

    var firstEpisode: String? = null
}
