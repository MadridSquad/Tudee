package com.washingtondcsquad.tudee.data.localSource

import com.washingtondcsquad.tudee.data.localSource.entities.TaskEntity
import com.washingtondcsquad.tudee.data.services.TaskLocalDataSource

class TaskLocalDataSourceImpl(
    private val daoTask: DaoTask
) : TaskLocalDataSource {

    override suspend fun createTask(task: TaskEntity) {
        daoTask.createTask(task)
    }

    override suspend fun editTask(task: TaskEntity) {
        daoTask.editTask(task)
    }

    override suspend fun deleteTask(task: TaskEntity) {
        daoTask.deleteTask(task)
    }

    override suspend fun getAllTasks(): List<TaskEntity> {
        return daoTask.getAllTasks()
    }

    override suspend fun getTaskById(taskId: String): TaskEntity? {
        return daoTask.getTaskById(taskId)
    }
}