package com.washingtondcsquad.tudee.domain.services

import com.washingtondcsquad.tudee.domain.entity.Task
import java.util.UUID

interface TasksService {
    suspend fun addTask(task: Task)
    suspend fun deleteTask(id: UUID)
    suspend fun getTasks(): List<Task>
    suspend fun getTaskById(id: UUID): Task
    suspend fun editTask(task: Task)
}