package com.example.testzara.view.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testzara.model.Data
import com.example.testzara.ui.theme.testZaraTheme
import com.example.testzara.view.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewModel.getCharacters()
        mainActivityViewModel.data.observe(this) {
            it
        }
        setContent {
            testZaraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TodoList()
                }
            }
        }
    }
}

@Composable
fun TodoList() {
    val mainViewModel: MainActivityViewModel = viewModel()
    val regions by mainViewModel.data.asFlow().collectAsState(initial = Data())
    val todoItems = remember { mutableStateListOf<Data>() }
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            /*Button(
                onClick = {
                    todoItems.add(
                        Region(
                            "Título ${todoItems.size + 1}",
                            "Descripción ${todoItems.size + 1}"
                        )
                    )
                }
            ) {
                Text("Agregar Elemento")
            }*/
        }

        LazyColumn {
            items(items = todoItems) { todoItem ->
                TodoItemRow(todoItem = todoItem) {
                    // Función de clic para cada elemento
                }
            }
        }
    }
}

@Composable
fun TodoItemRow(todoItem: Data, onItemClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
            .padding(8.dp)
    ) {
        /*Column {
            Text(text = todoItem.title)
            Text(text = todoItem.description)
        }*/
        Spacer(modifier = Modifier.weight(1f))
        val isChecked = remember { mutableStateOf(false) }
        /*Checkbox(
            checked = isChecked.value,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Green,
                uncheckedColor = Color.Red
            ),
            onCheckedChange = {
                todoItem.isCompleted = it
                isChecked.value = it
            }
        )*/
        /*Button(
            onClick = {
                // Eliminar el elemento del RecyclerView
                todoItem.isCompleted = true
            }
        ) {
            Text("Eliminar")
        }*/
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    testZaraTheme {
        TodoList()
    }
}