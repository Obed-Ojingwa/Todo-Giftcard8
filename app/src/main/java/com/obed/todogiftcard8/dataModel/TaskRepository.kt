package com.obed.todogiftcard8.dataModel

interface TaskRepository {
    suspend fun getTasks(): List<Task>
    suspend fun addTask(title: String, userId: Int): Task
    suspend fun updateTask(task: Task): Task
    suspend fun deleteTask(id: Int)
}
