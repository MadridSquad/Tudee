package com.washingtondcsquad.tudee.data.services

import com.washingtondcsquad.tudee.data.localSource.daos.DaoTask
import com.washingtondcsquad.tudee.data.localSource.mapper.category.toEntity
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.services.TasksService

class TasksServiceImpl(
    private val dao: DaoTask
) : TasksService {
    override suspend fun createTask(task: Task) {
        dao.createTask(task.toEntity())
    }

    override suspend fun deleteTask(task: Task) {
        dao.deleteTask(task.toEntity())
    }

    override suspend fun getAllTasks(): List<Task> {
       return dao.getAllTasks().map { it.toDomain() }
    }

    override suspend fun getTaskById(id: Int): Task {
        return dao.getTaskById(id).toDomain()
    }

    override suspend fun editTask(task: Task) {
        dao.editTask(task.toEntity())
    }

}