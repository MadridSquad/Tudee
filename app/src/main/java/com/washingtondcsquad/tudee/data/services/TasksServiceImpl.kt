package com.washingtondcsquad.tudee.data.services

import android.util.Log
import com.washingtondcsquad.tudee.data.localSource.daos.TaskDao
import com.washingtondcsquad.tudee.data.localSource.mapper.toDomain
import com.washingtondcsquad.tudee.data.localSource.mapper.toEntity
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.entity.TaskID
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import com.washingtondcsquad.tudee.domain.services.TasksService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class TasksServiceImpl(
    private val dao: TaskDao
) : TasksService {
    override suspend fun createTask(task: Task) {
        Log.e("MY_TAG","i am in create ")
        dao.createTask(task.toEntity())
    }

    override suspend fun deleteTask(task: Task) {
        dao.deleteTask(task.toEntity())
    }

    override fun getAllTasks(): Flow<List<Task>> {
        return dao.getAllTasks().map { flow ->
            flow.map {
                Log.e("MY_TAG",it.toString())

                it.toDomain()
            }
        }
    }

    override suspend fun getTaskById(id: TaskID): Task {
        return dao.getTaskById(id).toDomain()
    }

    override suspend fun editTask(task: Task) {
        dao.editTask(task.toEntity())
    }
}