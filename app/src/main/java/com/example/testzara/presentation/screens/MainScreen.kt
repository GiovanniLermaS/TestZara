package com.example.testzara.presentation.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.testzara.data.model.Character
import com.example.testzara.presentation.ui.composables.CardView
import com.example.testzara.presentation.ui.composables.Loader
import com.example.testzara.presentation.ui.composables.LoaderState
import com.example.testzara.presentation.ui.composables.SearchField
import com.example.testzara.presentation.view.viewmodel.view.MainActivityViewModel

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun MainScreen(navController: NavController, viewModel: ViewModel) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column {
            SearchField(navController, viewModel as MainActivityViewModel)
            Box {
                Loader()
                ResponseData(navController, viewModel)
            }
        }
    }
}

@Composable
fun ResponseData(navController: NavController, mainViewModel: MainActivityViewModel) {
    val characters = mainViewModel.data.collectAsState().value
    if (characters.isNotEmpty()) {
        LoaderState.isLoading = false
        Characters(characters = characters, navController, mainViewModel)
    }
    val error = mainViewModel.error.collectAsState().value
    if (!error.isNullOrEmpty()) Toast.makeText(LocalContext.current, error, Toast.LENGTH_LONG)
        .show()
}

@Composable
fun Characters(
    characters: List<Character>, navController: NavController, mainViewModel: MainActivityViewModel
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), state = lazyListState
    ) {
        items(characters) { character ->
            CardView(character, navController)
        }
    }
    lazyListState.OnBottomReached(mainViewModel)
}

@Composable
fun LazyListState.OnBottomReached(
    viewModel: MainActivityViewModel
) {
    val shouldLoadMore = remember {
        derivedStateOf {
            layoutInfo.visibleItemsInfo.isNotEmpty() && layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
        }
    }
    if (shouldLoadMore.value) {
        LoaderState.isLoading = true
        viewModel.getCharacters()
    }
}