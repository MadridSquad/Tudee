package com.washingtondcsquad.tudee.domain.services

import com.washingtondcsquad.tudee.domain.entity.Task
import java.util.UUID

interface TasksService {
    suspend fun createTask(task: Task)
    suspend fun deleteTask(id: UUID)
    suspend fun getAllTasks(): List<Task>
    suspend fun getTaskById(id: UUID): Task
    suspend fun editTask(task: Task)
}