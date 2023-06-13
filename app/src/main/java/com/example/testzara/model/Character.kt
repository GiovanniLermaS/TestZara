package com.example.testzara.model

data class Character(
    var name: String,
    var status: String,
    var species: String,
    var location: Location,
    var image: String,
    var episode: ArrayList<String>
)