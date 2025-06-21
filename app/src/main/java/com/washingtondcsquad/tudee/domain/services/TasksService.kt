package com.washingtondcsquad.tudee.domain.services

import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.entity.TaskID
import kotlinx.coroutines.flow.Flow

interface TasksService {
    suspend fun createTask(task: Task) //TODO create data object for task request
    suspend fun deleteTask(task: Task) //TODO if change task count will send here task id only
    fun getAllTasks(): Flow<List<Task>>
    suspend fun getTaskById(id: TaskID): Task
    suspend fun editTask(task: Task)
}
