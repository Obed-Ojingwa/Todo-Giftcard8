package com.obed.todogiftcard8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.obed.todogiftcard8.dataModel.Task
import com.obed.todogiftcard8.dataModel.TaskViewModel
import com.obed.todogiftcard8.navigation.EditTaskScreen
import com.obed.todogiftcard8.navigation.Screen
import com.obed.todogiftcard8.screens.TaskListScreen
import com.obed.todogiftcard8.ui.theme.TodoGiftcard8Theme
import dagger.hilt.android.AndroidEntryPoint

import com.obed.todogiftcard8.screens.AddEditTaskScreen


import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoGiftcard8Theme{
                val navController = rememberNavController()

             //   val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "task_list"
                ) {
                    // 🗂 Task List Screen
                    composable("task_list") {
                        TaskListScreen(navController = navController)
                    }

                    // 📝 Edit Task Screen
                    composable(
                        route = "edit_task/{taskId}",
                        arguments = listOf(navArgument("taskId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val taskId = backStackEntry.arguments?.getInt("taskId") ?: return@composable
                        EditTaskScreen(taskId = taskId, navController = navController)
                    }
                }
            }
        }
    }
}



//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//
//        setContent {
//            val navController = rememberNavController()
//
//            NavHost(navController = navController, startDestination = Screen.TaskList.route) {
//                composable(Screen.TaskList.route) {
//                    TaskListScreen(
//                        onEditClick = { taskId ->
//                            navController.navigate(Screen.EditTask.withArgs(taskId))
//                        }
//                    )
//                }
//
//                composable(
//                    route = Screen.EditTask.route,
//                    arguments = listOf(navArgument("taskId") { type = NavType.IntType })
//                ) { backStackEntry ->
//                    val taskId = backStackEntry.arguments?.getInt("taskId") ?: return@composable
//                    EditTaskScreen(taskId = taskId, navController = navController)
//                }
//            }
//        }
//        }
//    }
//
//
//
//@Preview(showBackground = true)
//@Composable
//fun TaskListScreenPreview() {
//    val sampleTasks = listOf(
//        Task(1, "Buy groceries", "Milk, Eggs, Bread", false),
//        Task(2, "Walk the dog", completed = true),
//    )
//    TaskListScreen(
//        tasks = sampleTasks,
//        onAddClick = {},
//        onEditClick = {},
//        onDeleteClick = {},
//        onToggleComplete = {}
//    )
//}*/

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}*/

/*setContent {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.TaskList.route) {
        composable(Screen.TaskList.route) {
            TaskListScreen(
                onEditClick = { taskId ->
                    navController.navigate(Screen.EditTask.withArgs(taskId))
                }
            )

    *//*
            TodoGiftcard8Theme {

                TaskListScreenPreview()
               *//**//* Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }*//*
            }*/


/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoGiftcard8Theme {
        Greeting("Android")
    }
}*/
