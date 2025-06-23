package com.washingtondcsquad.tudee.data.localSource.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.washingtondcsquad.tudee.data.localSource.model.TaskEntity
import com.washingtondcsquad.tudee.domain.entity.TaskID
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(taskId: TaskEntity)

    @Update
    suspend fun editTask(taskId: TaskEntity)

    @Query("SELECT * FROM ${TaskEntity.TASK_TABLE_NAME} WHERE id = :taskId")
    suspend fun getTaskById(taskId: TaskID): TaskEntity

    @Query("SELECT * FROM ${TaskEntity.TASK_TABLE_NAME}")
    fun getAllTasks(): Flow<List<TaskEntity>>
}