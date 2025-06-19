package com.washingtondcsquad.tudee.data.dataSources

import com.washingtondcsquad.tudee.data.localSource.entities.TaskEntity

interface TaskLocalDataSource {
    suspend fun getAllTasks(): List<TaskEntity>

    suspend fun deleteTask(task:TaskEntity)
}