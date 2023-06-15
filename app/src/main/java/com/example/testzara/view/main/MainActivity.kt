package com.example.testzara.view.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.testzara.model.Character
import com.example.testzara.ui.theme.testZaraTheme
import com.example.testzara.view.viewmodel.view.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import testzara.R

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewModel.getCharacters()
        setContent {
            testZaraTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        SearchField()
                        Loader()
                    }
                }
            }
        }
    }
}

@Composable
fun SearchField(
    mainViewModel: MainActivityViewModel = viewModel()
) {
    val focusManager = LocalFocusManager.current
    var searchQuery: String? by remember { mutableStateOf(null) }
    OutlinedTextField(
        value = searchQuery ?: "",
        onValueChange = { searchQuery = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        placeholder = { Text(text = "Search") },
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        colors = outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.gray),
            unfocusedBorderColor = colorResource(id = R.color.gray),
            textColor = colorResource(id = R.color.white),
            cursorColor = colorResource(id = R.color.white),
            placeholderColor = colorResource(id = R.color.white)
        ),
        textStyle = TextStyle(fontWeight = FontWeight.Bold),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() })
    )
    searchQuery?.let { query ->
        val isFiltering: Boolean
        val filter = if (searchQuery == "") {
            Log.e("Query", "" + searchQuery)
            isFiltering = false
            mainViewModel.data.value
        } else {
            isFiltering = true
            mainViewModel.data.value?.filter {
                it.name?.lowercase()?.contains(query.lowercase()) == true
            }
        }
        Characters(characters = filter as ArrayList<Character>, isFiltering)
    }
}

@Composable
fun Loader(mainViewModel: MainActivityViewModel = viewModel()) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    val progress by animateLottieCompositionAsState(composition)
    if (mainViewModel.showProgress.value)
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f)
                .pointerInput(Unit) {}
        )
    mainViewModel.data.value?.let {
        Characters(it, false)
    }
}

@Composable
fun Characters(
    characters: ArrayList<Character>,
    isFiltering: Boolean,
    mainViewModel: MainActivityViewModel = viewModel()
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        state = lazyListState
    ) {
        items(characters) { character ->
            CardView(character)
        }
    }
    val visibleItemCount = lazyListState.layoutInfo.visibleItemsInfo.size
    val totalItemCount = lazyListState.layoutInfo.totalItemsCount
    val lastVisibleItemIndex = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index

    if (visibleItemCount > 0
        && lastVisibleItemIndex == totalItemCount - 1
        && !mainViewModel.showProgress.value
        && !isFiltering
    ) mainViewModel.getCharacters()
}

@Composable
fun CardView(character: Character) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(bottom = 16.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(character.image),
                contentDescription = character.name,
                modifier = Modifier
                    .width(185.dp)
                    .fillMaxHeight(),
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text(
                        text = character.name ?: "",
                        fontSize = 25.sp,
                        color = colorResource(id = R.color.white),
                    )
                    Row {
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .background(
                                    color = colorResource(
                                        id = when (character.status?.lowercase()) {
                                            stringResource(id = R.string.alive) -> R.color.green
                                            stringResource(id = R.string.dead) -> R.color.red
                                            else -> R.color.gray
                                        }
                                    ),
                                    shape = CircleShape
                                )
                                .align(Alignment.CenterVertically)
                        )
                        Text(
                            text = "${character.status} - ${character.species}",
                            fontSize = 13.sp,
                            color = colorResource(id = R.color.white),
                            modifier = Modifier.padding(start = 7.dp)
                        )
                    }
                }
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text(
                        text = stringResource(R.string.last_know_location),
                        color = colorResource(id = R.color.gray)
                    )
                    Text(
                        text = character.location.name ?: "",
                        fontSize = 13.sp,
                        color = colorResource(id = R.color.white)
                    )
                }
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    Text(
                        text = stringResource(R.string.first_seen_in),
                        color = colorResource(id = R.color.gray)
                    )
                    Text(
                        text = character.firstEpisode ?: "",
                        fontSize = 13.sp,
                        color = colorResource(id = R.color.white)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    testZaraTheme {
        Loader()
    }
}