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
interface TudeeDaoCategory {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createCategory(category: CategoryEntity)

    @Delete
    fun deleteCategory(category: CategoryEntity)

    @Update
    fun editCategory(category: CategoryEntity)

    @Query("SELECT * FROM ${Constants.ROOM_DATABASE_NAME}")
    fun getAllCategories(): List<CategoryEntity>

    @Query("SELECT * FROM ${Constants.ROOM_DATABASE_NAME} WHERE id = :categoryId")
    fun getCategoryById(categoryId: Long): CategoryEntity

}