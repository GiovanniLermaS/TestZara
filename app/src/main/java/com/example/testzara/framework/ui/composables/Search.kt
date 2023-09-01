package com.example.testzara.framework.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testzara.R
import com.example.testzara.domain.Character
import com.example.testzara.framework.screens.Characters
import com.example.testzara.framework.view.viewmodel.view.MainActivityViewModel

@Composable
fun SearchField(
    navController: NavController, mainViewModel: MainActivityViewModel
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
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.gray),
            unfocusedBorderColor = colorResource(id = R.color.gray),
            textColor = colorResource(id = R.color.white),
            cursorColor = colorResource(id = R.color.white),
            placeholderColor = colorResource(id = R.color.white)
        ),
        textStyle = TextStyle(fontWeight = FontWeight.Bold),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() })
    )
    searchQuery?.let { query ->
        val filter = if (searchQuery == "") {
            LoaderState.isLoading = true
            mainViewModel.data.value
        } else {
            LoaderState.isLoading = false
            mainViewModel.data.value.filter {
                it.name?.lowercase()?.contains(query.lowercase()) == true
            }
        }
        Characters(characters = filter as ArrayList<Character>, navController, mainViewModel)
    }
}