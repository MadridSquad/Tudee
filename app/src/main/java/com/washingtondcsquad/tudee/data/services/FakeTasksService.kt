package com.washingtondcsquad.tudee.data.services

import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.services.TasksService
import java.util.UUID

class FakeTasksService : TasksService {
    private val tasks = mutableListOf<Task>()
    override suspend fun addTask(task: Task) {
        this.tasks.add(task)
    }

    override suspend fun deleteTask(id: UUID) {
        tasks.removeIf { it.id == id }
    }

    override suspend fun getTasks(): List<Task> {
        return tasks
    }

    override suspend fun getTaskById(id: UUID): Task {
        return tasks.find { it.id == id }!!
    }

    override suspend fun editTask(task: Task) {
        val index = tasks.indexOfFirst { it.id == task.id }
        tasks[index] = task
    }
}