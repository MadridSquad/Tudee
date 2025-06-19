package com.washingtondcsquad.tudee.data.localSource.dataSourcesImplementations

import com.washingtondcsquad.tudee.data.dataSources.TaskLocalDataSource
import com.washingtondcsquad.tudee.data.localSource.daos.DaoTask
import com.washingtondcsquad.tudee.data.localSource.entities.TaskEntity

class TaskLocalDataSourceImpl(
    private val dao: DaoTask
) : TaskLocalDataSource {

    override suspend fun getAllTasks(): List<TaskEntity> = dao.getAllTasks()

    override suspend fun deleteTask(task: TaskEntity) =dao.deleteTask(task)
}