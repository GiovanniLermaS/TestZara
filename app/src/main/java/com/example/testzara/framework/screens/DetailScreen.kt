package com.example.testzara.framework.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.testzara.domain.Character
import com.example.testzara.framework.ui.composables.CardView
import com.google.gson.Gson

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun DetailScreen(navController: NavController, characterJson: String?) {
    val character = Gson().fromJson(characterJson, Character::class.java)
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column {
            CardView(character = character, navController = navController)
        }
    }
}