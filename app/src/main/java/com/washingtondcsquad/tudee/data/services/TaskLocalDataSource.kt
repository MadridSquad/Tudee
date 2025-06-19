package com.washingtondcsquad.tudee.data.services

import com.washingtondcsquad.tudee.data.localSource.entities.TaskEntity

interface TaskLocalDataSource {
    suspend fun createTask(task: TaskEntity)
    suspend fun editTask(task: TaskEntity)
    suspend fun deleteTask(task: TaskEntity)
    suspend fun getAllTasks(): List<TaskEntity>
    suspend fun getTaskById(taskId: String): TaskEntity?
}