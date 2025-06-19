package com.washingtondcsquad.tudee.data.localSource.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.washingtondcsquad.tudee.data.localSource.entities.TaskEntity
import com.washingtondcsquad.tudee.data.utils.DataBaseConstants


@Dao
interface DaoTask {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(taskId: TaskEntity)


    @Update
    suspend fun editTask(taskId: TaskEntity)


    @Query("SELECT * FROM ${DataBaseConstants.TASK_TABLE_NAME} WHERE id = :taskId")
    suspend fun getTaskById(taskId: Int): TaskEntity


    @Query("SELECT * FROM ${DataBaseConstants.TASK_TABLE_NAME}")
    suspend fun getAllTasks(): List<TaskEntity>


}