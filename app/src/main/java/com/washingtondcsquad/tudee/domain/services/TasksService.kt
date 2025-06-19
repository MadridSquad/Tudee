package com.washingtondcsquad.tudee.domain.services

import com.washingtondcsquad.tudee.domain.entity.Task

interface TasksService {
    suspend fun createTask(task: Task)
    suspend fun deleteTask(id: Int)
    suspend fun getAllTasks(): List<Task>
    suspend fun getTaskById(id: Int): Task
    suspend fun editTask(task: Task)
}