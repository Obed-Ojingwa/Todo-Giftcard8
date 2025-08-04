package com.obed.todogiftcard8.di

import android.content.Context
import androidx.room.Room
import com.obed.todogiftcard8.data.internetworkmanager.ApiService
import com.obed.todogiftcard8.dataModel.TaskRepository
import com.obed.todogiftcard8.repository.TaskRepositoryImpl
import com.obed.todogiftcard8.room.interfac.AppDatabase
import com.obed.todogiftcard8.room.interfac.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "todo_database"
        ).build()
    }

    @Provides
    fun provideTaskDao(db: AppDatabase): TaskDao = db.taskDao()

    @Provides
    @Singleton
    fun provideRepository(api: ApiService, dao: TaskDao): TaskRepository {
        return TaskRepositoryImpl(api, dao)
    }
}

/*
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTaskRepository(apiService: ApiService): TaskRepository {
        return TaskRepositoryImpl(apiService)
    }
}*/
