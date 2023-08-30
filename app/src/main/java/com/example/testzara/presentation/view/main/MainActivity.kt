package com.example.testzara.presentation.view.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testzara.presentation.navigation.AppNavigation
import com.example.testzara.presentation.ui.theme.TestZaraTheme
import com.example.testzara.presentation.view.viewmodel.view.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewModel.getCharacters()
        setContent {
            TestZaraTheme {
                AppNavigation(mainActivityViewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(mainViewModel: MainActivityViewModel = viewModel()) {
    TestZaraTheme {
        AppNavigation(mainViewModel)
    }
}