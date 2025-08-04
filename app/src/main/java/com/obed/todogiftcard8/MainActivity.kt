package com.obed.todogiftcard8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.obed.todogiftcard8.dataModel.Task
import com.obed.todogiftcard8.screens.TaskListScreen
import com.obed.todogiftcard8.ui.theme.TodoGiftcard8Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoGiftcard8Theme {

                TaskListScreenPreview()
               /* Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }*/
            }
        }
    }
}

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}*/

@Preview(showBackground = true)
@Composable
fun TaskListScreenPreview() {
    val sampleTasks = listOf(
        Task(1, "Buy groceries", "Milk, Eggs, Bread", false),
        Task(2, "Walk the dog", completed = true),
    )
    TaskListScreen(
        tasks = sampleTasks,
        onAddClick = {},
        onEditClick = {},
        onDeleteClick = {},
        onToggleComplete = {}
    )
}


/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoGiftcard8Theme {
        Greeting("Android")
    }
}*/
