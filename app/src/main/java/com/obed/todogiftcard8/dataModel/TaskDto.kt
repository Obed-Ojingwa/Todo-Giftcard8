package com.obed.todogiftcard8.dataModel


data class TaskDto(
    val id: Int,
    val todo: String,
    val completed: Boolean,
    val userId: Int
)
