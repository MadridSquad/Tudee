package com.washingtondcsquad.tudee.domain.services

import com.washingtondcsquad.tudee.domain.entity.Task
import kotlinx.coroutines.flow.Flow

interface TasksService {
    suspend fun createTask(task: Task)
    suspend fun deleteTask(task: Task)
    fun getAllTasks(): Flow<List<Task>>
    suspend fun getTaskById(id: Int): Task
    suspend fun editTask(task: Task)
}