package com.example.testzara.model

import com.google.gson.annotations.SerializedName

class Result {

    @SerializedName("name")
    var name: String? = ""

    @SerializedName("url")
    var url: String? = ""
}