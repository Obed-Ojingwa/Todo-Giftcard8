package com.obed.todogiftcard8.dataModel

data class Task(
    val id: Int,
    val title: String,
    val description: String? = null,
    val completed: Boolean = false
)
