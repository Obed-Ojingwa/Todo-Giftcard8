package com.obed.todogiftcard8.data.internetworkmanager

import com.obed.todogiftcard8.dataModel.AddTaskRequest
import com.obed.todogiftcard8.dataModel.TaskDto
import com.obed.todogiftcard8.dataModel.TaskResponse
import com.obed.todogiftcard8.dataModel.UpdateTaskRequest
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface ApiService {

    @GET("todos")
    suspend fun getTasks(): TaskResponse

    @POST("todos/add")
    suspend fun addTask(@Body task: AddTaskRequest): TaskDto

    @PUT("todos/{id}")
    suspend fun updateTask(
        @Path("id") id: Int,
        @Body task: UpdateTaskRequest
    ): TaskDto

    @DELETE("todos/{id}")
    suspend fun deleteTask(@Path("id") id: Int): Response<Unit>
}
