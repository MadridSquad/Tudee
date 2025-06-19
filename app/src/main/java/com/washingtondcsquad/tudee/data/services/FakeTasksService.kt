package com.washingtondcsquad.tudee.data.services

import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.services.TasksService

class FakeTasksService : TasksService {
    private val tasks = mutableListOf<Task>(
//        Task(
//            id = 1,
//            categoryId = 1.toLong(),
//            categoryImage = "",
//            title = "Buy groceries",
//            description = "Milk, Bread, Eggs, and Fruits",
//            date = java.time.LocalDate.now().toString(),
//            status = "Pending",
//            priority = "High"
//        ),
//        Task(
//            id = 2,
//            categoryId = 2.toLong(),
//            categoryImage = "",
//            title = "Read a book",
//            description = "Finish reading Kotlin in Action",
//            date = java.time.LocalDate.now().plusDays(1).toString(),
//            status = "Pending",
//            priority = "Normal"
//        ),
//        Task(
//            id = 3,
//            categoryId = 3.toLong(),
//            categoryImage = "",
//            title = "Workout",
//            description = "30 minutes of cardio",
//            date = java.time.LocalDate.now().plusDays(2).toString(),
//            status = "Completed",
//            priority = "Low"
//        ),
//        Task(
//            id = 4,
//            categoryId = 4.toLong(),
//            categoryImage = "",
//            title = "Meeting",
//            description = "Team meeting at 3 PM",
//            date = java.time.LocalDate.now().plusDays(3).toString(),
//            status = "Pending",
//            priority = "High"
//        ),
//        Task(
//            id = 5,
//            categoryId = 5.toLong(),
//            categoryImage = "",
//            title = "Call mom",
//            description = "Check in with mom",
//            date = java.time.LocalDate.now().plusDays(4).toString(),
//            status = "Pending",
//            priority = "Normal"
//        )
    )

    override suspend fun createTask(task: Task) {
        this.tasks.add(task)
    }

    override suspend fun deleteTask(id: Int) {
        tasks.removeIf { it.id == id }
    }

    override suspend fun getAllTasks(): List<Task> {
        return tasks
    }

    override suspend fun getTaskById(id: Int): Task {
        return tasks.find { it.id == id }!!
    }

    override suspend fun editTask(task: Task) {
        val index = tasks.indexOfFirst { it.id == task.id }
        tasks[index] = task
    }
}