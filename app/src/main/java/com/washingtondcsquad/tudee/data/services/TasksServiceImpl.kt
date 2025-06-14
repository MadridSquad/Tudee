package com.washingtondcsquad.tudee.data.services

import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.services.TasksService
import java.util.UUID

class TasksServiceImpl : TasksService {
    override suspend fun addTask(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(id: UUID) {
        TODO("Not yet implemented")
    }

    override suspend fun getTasks(): List<Task> {
        TODO("Not yet implemented")
    }

    override suspend fun getTaskById(id: UUID): Task {
        TODO("Not yet implemented")
    }

    override suspend fun editTask(task: Task) {
        TODO("Not yet implemented")
    }
}