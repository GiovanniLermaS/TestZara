package com.example.testzara.framework.ui.composables

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.testzara.R
import com.example.testzara.domain.Character
import com.example.testzara.framework.navigation.AppScreens
import com.google.gson.Gson

@Composable
fun CardView(
    character: Character, navController: NavController
) {
    androidx.compose.material3.Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(bottom = 16.dp)
            .clickable {
                navController.navigate(
                    route = AppScreens.DetailActivity.route + "/" + Uri
                        .encode(
                            Gson().toJson(
                                character
                            )
                        )
                        .toString()
                )
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray,
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
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
                                    ), shape = CircleShape
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