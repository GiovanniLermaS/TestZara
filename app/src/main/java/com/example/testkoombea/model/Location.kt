package com.example.testkoombea.model

import com.google.gson.annotations.SerializedName
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Location : RealmObject {

    @PrimaryKey
    var _id: ObjectId = ObjectId()

    @SerializedName("name")
    var name: String? = null
}