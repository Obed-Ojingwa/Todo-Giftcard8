package com.obed.todogiftcard8.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obed.todogiftcard8.dataModel.Task
import com.obed.todogiftcard8.dataModel.TaskRepository
import com.obed.todogiftcard8.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/*

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
): ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadTasks()
    }

    fun loadTasks() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _tasks.value = repository.getTasks()
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
*/
