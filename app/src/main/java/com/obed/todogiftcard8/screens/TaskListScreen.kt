package com.obed.todogiftcard8.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.obed.todogiftcard8.components.TaskItem

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.obed.todogiftcard8.dataModel.TaskViewModel
import com.obed.todogiftcard8.dataModel.errorDisplay.AddTaskDialog
import com.obed.todogiftcard8.navigation.Screen
import kotlinx.coroutines.launch


import com.obed.todogiftcard8.dataModel.Task
/*
@Composable
fun TaskListScreen(
    viewModel: TaskViewModel,
    onAddClick: () -> Unit,
    onEditClick: (Task) -> Unit,
    onDeleteClick: (Task) -> Unit,
    onToggleComplete: (Task) -> Unit
) {
    // All UI logic inside this
}*/





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    navController: NavController,
    viewModel: TaskViewModel = hiltViewModel()
) {
    val tasks by viewModel.tasks.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var showDialog by remember { mutableStateOf(false) }

    // Fetch tasks on launch
    LaunchedEffect(Unit) {
        viewModel.loadTasks()
    }

    // Show snackbar for any error
    LaunchedEffect(error) {
        error?.let {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(it)
                viewModel.clearError()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("My To-Do List") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                items(tasks) { task ->
                    TaskItem(
                        task = task,
                        onEditClick = {
                            navController.navigate("edit_task/${task.id}")
                        },
                        onDeleteClick = {
                            viewModel.deleteTask(it.id)
                        },
                        onToggleComplete = {
                            val updated = it.copy(completed = !it.completed)
                            viewModel.updateTask(updated)
                        }
                    )
                }
            }
        }
    }

    // Dialog for adding task
    if (showDialog) {
        AddTaskDialog(
            onDismiss = { showDialog = false },
            onAdd = { title ->
                viewModel.addTask(title = title)
            }
        )
    }
}



/*
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

        *//*
         )
            Column(modifier = Modifier.weight(1f)) {
                Text(

                task.description?.let {
                    Text(text ="Testing")
                }*//*
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
}*//*
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TaskListScreen(
    viewModel: TaskViewModel = hiltViewModel()
) {
    val tasks by viewModel.tasks.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var showDialog by remember { mutableStateOf(false) }

    // Auto load once
    LaunchedEffect(Unit) {
        viewModel.loadTasks()
    }

    // Show snackbar for errors
    LaunchedEffect(error) {
        error?.let {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(it)
                viewModel.clearError()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("My To-Do List") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(padding)
            ) {
                items(tasks) { task ->
                    TaskItem(
                        task = task,
                        onEditClick = { task ->
                            navController.navigate(Screen.EditTask.withArgs(task.id))
                        }
                        ,
                        onDeleteClick = {
                            viewModel.deleteTask(it.id)
                        },
                        onToggleComplete = {
                            val updated = it.copy(completed = !it.completed)
                            viewModel.updateTask(updated)
                        }
                    )
                }
            }
        }
    }

    // Dialog for adding a task
    if (showDialog) {
        AddTaskDialog(
            onDismiss = { showDialog = false },
            onAdd = { title ->
                viewModel.addTask(title = title)
            }
        )
    }
}*/


/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    viewModel: TaskViewModel = hiltViewModel(),  // inject ViewModel here
    onAddClick: () -> Unit,
    onEditClick: (Task) -> Unit,
    onDeleteClick: (Task) -> Unit,
    onToggleComplete: (Task) -> Unit
) {
    val tasks by viewModel.tasks.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    // Automatically load tasks when screen is first composed
    LaunchedEffect(Unit) {
        viewModel.loadTasks()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("My To-Do List") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) { padding ->
        if (isLoading) {
            Text("Loading...", modifier = Modifier.padding(padding))
        } else if (error != null) {
            Text("Error: $error", modifier = Modifier.padding(padding))
        } else {
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
}*/


/*
* @Composable
fun TaskListScreen(
    viewModel: TaskViewModel = hiltViewModel(),
    onAddClick: () -> Unit,
    ...
) {
    val tasks by viewModel.tasks.collectAsState()
    val loading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    if (loading) {
        CircularProgressIndicator()
    } else if (error != null) {
        Text("Error: $error")
    } else {
        LazyColumn {
            items(tasks) { task ->
                TaskItem(
                    task = task,
                    ...
                )
            }
        }
    }
}
*/