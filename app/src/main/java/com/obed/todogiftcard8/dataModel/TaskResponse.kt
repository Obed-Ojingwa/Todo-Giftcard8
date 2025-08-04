package com.obed.todogiftcard8.dataModel





data class TaskResponse(
    val todos: List<TaskDto>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

