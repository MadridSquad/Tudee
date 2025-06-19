package com.washingtondcsquad.tudee.data.services

import android.util.Log
import com.washingtondcsquad.tudee.data.localSource.mapper.category.toDomain
import com.washingtondcsquad.tudee.data.localSource.mapper.category.toEntity
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.services.TasksService
import java.util.UUID

class TasksServiceImpl(
    private val taskLocalDataSource: TaskLocalDataSource
) : TasksService {
    override suspend fun createTask(task: Task) {
        Log.d("TasksServiceImpl", "Creating task: $task")
        taskLocalDataSource.createTask(task.toEntity())
    }

    override suspend fun deleteTask(id: UUID) {
        val taskEntity = taskLocalDataSource.getTaskById(id.toString())
        taskEntity?.let {
            taskLocalDataSource.deleteTask(it)
        }
    }

    override suspend fun getAllTasks(): List<Task> {
        return taskLocalDataSource.getAllTasks().map { it.toDomain() }
    }

    override suspend fun getTaskById(id: UUID): Task {
        val taskEntity = taskLocalDataSource.getTaskById(id.toString())
        return taskEntity?.toDomain() ?: throw NoSuchElementException("Task with ID $id not found in database.")
    }

    override suspend fun editTask(task: Task) {
        taskLocalDataSource.editTask(task.toEntity())
    }
}