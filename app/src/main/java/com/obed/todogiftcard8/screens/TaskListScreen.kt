package com.obed.todogiftcard8.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.obed.todogiftcard8.components.TaskItem
import com.obed.todogiftcard8.dataModel.Task


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    tasks: List<Task>,
    onAddClick: () -> Unit,
    onEditClick: (Task) -> Unit,
    onDeleteClick: (Task) -> Unit,
    onToggleComplete: (Task) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("My To-Do List") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        }

        /*
         )
            Column(modifier = Modifier.weight(1f)) {
                Text(

                task.description?.let {
                    Text(text ="Testing")
                }*/
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(tasks) { task ->
                TaskItem(
                    task = task,
                    onEditClick = { onEditClick(task) },
                    onDeleteClick = { onDeleteClick(task) },
                    onToggleComplete = { onToggleComplete(task) }
                )
            }
        }
    }
}
