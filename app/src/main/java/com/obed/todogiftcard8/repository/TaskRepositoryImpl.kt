package com.obed.todogiftcard8.repository

import com.obed.todogiftcard8.data.internetworkmanager.ApiService
import com.obed.todogiftcard8.dataModel.AddTaskRequest
import com.obed.todogiftcard8.dataModel.Task
import com.obed.todogiftcard8.dataModel.TaskRepository
import com.obed.todogiftcard8.dataModel.UpdateTaskRequest
import com.obed.todogiftcard8.dataModel.mapper.toDomain
import com.obed.todogiftcard8.room.interfac.TaskDao
import com.obed.todogiftcard8.room.toDomain
import com.obed.todogiftcard8.room.toEntity


class TaskRepositoryImpl(
    private val api: ApiService,
    private val dao: TaskDao
) : TaskRepository {

    override suspend fun getTasks(): List<Task> {
        return try {
            val remoteTasks = api.getTasks().todos.map { it.toDomain() }
            dao.insertTasks(remoteTasks.map { it.toEntity() }) // cache locally
            remoteTasks
        } catch (e: Exception) {
            dao.getAllTasks().map { it.toDomain() } // fallback to local
        }
    }

    override suspend fun addTask(title: String, userId: Int): Task {
        val newTask = api.addTask(AddTaskRequest(title, false, userId)).toDomain()
        dao.insertTask(newTask.toEntity())
        return newTask
    }

    override suspend fun updateTask(task: Task): Task {
        val updated = api.updateTask(task.id, UpdateTaskRequest(task.title, task.completed)).toDomain()
        dao.insertTask(updated.toEntity())
        return updated
    }

    override suspend fun deleteTask(id: Int) {
        api.deleteTask(id)
        dao.deleteTaskById(id)
    }
}

/*class TaskRepositoryImpl(
    private val api: ApiService
): TaskRepository {

    override suspend fun getTasks(): List<Task> {
        return api.getTasks().todos.map { it.toDomain() }
    }

    override suspend fun addTask(title: String, userId: Int): Task {
        val request = AddTaskRequest(todo = title, userId = userId)
        return api.addTask(request).toDomain()
    }

    override suspend fun updateTask(task: Task): Task {
        val request = UpdateTaskRequest(todo = task.title, completed = task.completed)
        return api.updateTask(task.id, request).toDomain()
    }

    override suspend fun deleteTask(id: Int) {
        api.deleteTask(id)
    }
}*/
