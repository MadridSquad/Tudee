package com.washingtondcsquad.tudee.data.dataSources

import com.washingtondcsquad.tudee.data.localSource.entities.TaskEntity

interface TaskLocalDataSource {
    suspend fun createTask(task: TaskEntity)
    suspend fun getAllTasks(): List<TaskEntity>
}