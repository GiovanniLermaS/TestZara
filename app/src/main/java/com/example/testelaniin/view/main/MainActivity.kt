package com.example.testelaniin.view.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testelaniin.model.Region
import com.example.testelaniin.ui.theme.TestMVVMTheme
import com.example.testelaniin.view.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getRegions()
        viewModel.regions.observe(this) {
            Toast.makeText(this, it.results[0].name, Toast.LENGTH_SHORT).show()
        }
        setContent {
            TestMVVMTheme {
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
    val viewModel: MainActivityViewModel = viewModel()
    val todoItems = remember { mutableStateListOf<Region>() }
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
fun TodoItemRow(todoItem: Region, onItemClick: () -> Unit) {
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
    TestMVVMTheme {
        TodoList()
    }
}