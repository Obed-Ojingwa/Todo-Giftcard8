package com.obed.todogiftcard8.dataModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadTasks() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _tasks.value = repository.getTasks()
            } catch (e: Exception) {
                _error.value = "Failed to load tasks: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addTask(title: String, userId: Int = 1) {
        viewModelScope.launch {
            try {
                val newTask = repository.addTask(title, userId)
                _tasks.value = _tasks.value + newTask
            } catch (e: Exception) {
                _error.value = "Failed to add task: ${e.message}"
            }
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            try {
                val updated = repository.updateTask(task)
                _tasks.value = _tasks.value.map { if (it.id == updated.id) updated else it }
            } catch (e: Exception) {
                _error.value = "Failed to update task: ${e.message}"
            }
        }
    }

    fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            try {
                repository.deleteTask(taskId)
                _tasks.value = _tasks.value.filter { it.id != taskId }
            } catch (e: Exception) {
                _error.value = "Failed to delete task: ${e.message}"
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}
