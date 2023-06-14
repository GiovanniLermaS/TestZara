package com.example.testzara.view.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.testzara.model.Character
import com.example.testzara.ui.theme.testZaraTheme
import com.example.testzara.view.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewModel.getCharacters()
        setContent {
            testZaraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Characters()
                }
            }
        }
    }
}

@Composable
fun Characters(mainViewModel: MainActivityViewModel = viewModel()) {
    mainViewModel.data.value?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            CardView(it)
        }
    }
}

@Composable
fun CardView(
    listCharacters: ArrayList<Character>
) {
    listCharacters.forEach { character ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
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
                            color = colorResource(id = testzara.R.color.white),
                        )
                        Row {
                            Image(
                                painter = rememberAsyncImagePainter(character.image),
                                contentDescription = "Dead - Alive",
                                modifier = Modifier
                                    .size(5.dp),
                            )
                            Text(
                                text = "${character.status} - ${character.species}",
                                fontSize = 13.sp,
                                color = colorResource(id = testzara.R.color.white)
                            )
                        }
                    }
                    Column(modifier = Modifier.padding(bottom = 10.dp)) {
                        Text(
                            text = stringResource(testzara.R.string.last_know_location),
                            color = colorResource(id = testzara.R.color.gray)
                        )
                        Text(
                            text = character.location.name ?: "",
                            fontSize = 13.sp,
                            color = colorResource(id = testzara.R.color.white)
                        )
                    }
                    Column(modifier = Modifier.padding(bottom = 10.dp)) {
                        Text(
                            text = stringResource(testzara.R.string.first_seen_in),
                            color = colorResource(id = testzara.R.color.gray)
                        )
                        Text(
                            text = character.first_episode ?: "",
                            fontSize = 13.sp,
                            color = colorResource(id = testzara.R.color.white)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    testZaraTheme {
        Characters()
    }
}