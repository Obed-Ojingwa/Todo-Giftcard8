package com.obed.todogiftcard8.dataModel.mapper

import com.obed.todogiftcard8.dataModel.Task
import com.obed.todogiftcard8.dataModel.TaskDto


// data/mapper/TaskMapper.kt
fun TaskDto.toDomain(): Task {
    return Task(
        id = id,
        title = todo,
        completed = completed,
      //  userId = userId
    )
}
