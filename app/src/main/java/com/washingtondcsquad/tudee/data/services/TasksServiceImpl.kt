package com.washingtondcsquad.tudee.data.services

import com.washingtondcsquad.tudee.data.dataSources.TaskLocalDataSource
import com.washingtondcsquad.tudee.data.localSource.entities.TaskEntity
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.services.TasksService

class TasksServiceImpl(
    private val dataSource: TaskLocalDataSource
) : TasksService {
    override suspend fun createTask(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(task: Task) {

        dataSource.deleteTask(TaskEntity.fromDomain(task))
    }

    override suspend fun getAllTasks(): List<Task> = dataSource.getAllTasks().map { it.toDomain() }

    override suspend fun getTaskById(id: Int): Task {
        TODO("Not yet implemented")
    }

    override suspend fun editTask(task: Task) {
        TODO("Not yet implemented")
    }
}