package com.example.testkoombea.model

import com.google.gson.annotations.SerializedName
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.PrimaryKey


abstract class Character : RealmObject {

    @PrimaryKey
    @SerializedName("id")
    var id: Int? = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("status")
    var status: String? = null

    @SerializedName("species")
    var species: String? = null

    @SerializedName("location")
    var location: Location? = null

    @SerializedName("image")
    var image: String? = null

    @Ignore
    @SerializedName("episode")
    var episode = ArrayList<String>()

    var firstEpisode: String? = null
}
