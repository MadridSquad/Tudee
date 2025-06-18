package com.washingtondcsquad.tudee.data.localSource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.washingtondcsquad.tudee.data.localSource.entities.CategoryEntity
import com.washingtondcsquad.tudee.data.utils.Constants

@Dao
interface DaoCategory {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createCategory(category: CategoryEntity)

    @Delete
    suspend fun deleteCategory(category: CategoryEntity)

    @Update
    suspend fun editCategory(category: CategoryEntity)

    @Query("SELECT * FROM ${Constants.ROOM_DATABASE_NAME}")
    suspend fun getAllCategories(): List<CategoryEntity>

    @Query("SELECT * FROM ${Constants.ROOM_DATABASE_NAME} WHERE id = :categoryId")
    suspend fun getCategoryById(categoryId: Long): CategoryEntity

}