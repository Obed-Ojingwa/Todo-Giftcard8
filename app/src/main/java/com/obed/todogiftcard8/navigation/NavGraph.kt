package com.obed.todogiftcard8.navigation

sealed class Screen(val route: String) {
    object TaskList : Screen("task_list")
    object EditTask : Screen("edit_task/{taskId}") {
        fun withArgs(taskId: Int) = "edit_task/$taskId"
    }
}
