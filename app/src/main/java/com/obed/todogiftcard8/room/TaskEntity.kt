package com.obed.todogiftcard8.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.obed.todogiftcard8.dataModel.Task


@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val completed: Int,
    val userId: Int
)


fun TaskEntity.toDomain(): Task {
    return Task(id, title, completed, userId)
}

fun Task.toEntity(): TaskEntity {
    return TaskEntity(id, title, completed, userId = 0)
}
