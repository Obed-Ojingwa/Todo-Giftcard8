package com.obed.todogiftcard8.dataModel

data class AddTaskRequest(
    val todo: String,
    val completed: Boolean = false,
    val userId: Int
)
