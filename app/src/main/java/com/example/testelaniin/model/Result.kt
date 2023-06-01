package com.example.testelaniin.model

import com.google.gson.annotations.SerializedName

class Result {

    @SerializedName("name")
    var name: String? = ""

    @SerializedName("url")
    var url: String? = ""
}