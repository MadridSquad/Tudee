package com.washingtondcsquad.tudee.data.localSource.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.washingtondcsquad.tudee.data.localSource.model.CategoryEntity
import com.washingtondcsquad.tudee.data.localSource.model.TaskEntity
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.entity.TaskID
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(taskId: TaskEntity)

    @Update
    suspend fun editTask(taskId: TaskEntity)

    @Query("SELECT * FROM ${TaskEntity.TASK_TABLE_NAME} WHERE id =:taskId")
    suspend fun getTaskById(taskId: TaskID): TaskEntity


    @Query("SELECT * FROM ${TaskEntity.TASK_TABLE_NAME}")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM ${TaskEntity.TASK_TABLE_NAME} WHERE categoryId = :categoryId")
    suspend fun getTasksByCategoryId(categoryId: CategoryID): List<Task>

    @Query("SELECT COUNT(*) FROM ${TaskEntity.TASK_TABLE_NAME}  WHERE categoryId = :categoryId")
    suspend fun getTaskCountByCategoryId(categoryId: CategoryID): Int

}