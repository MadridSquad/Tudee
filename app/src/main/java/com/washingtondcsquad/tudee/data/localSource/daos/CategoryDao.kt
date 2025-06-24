package com.washingtondcsquad.tudee.data.localSource.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.washingtondcsquad.tudee.data.localSource.model.CategoryEntity
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createCategory(category: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createPredefinedCategories(category: List<CategoryEntity>)

    @Query("DELETE FROM ${CategoryEntity.CATEGORY_TABLE_NAME} WHERE id = :categoryId")
    suspend fun deleteCategory(categoryId : CategoryID)

    @Update
    suspend fun editCategory(category: CategoryEntity)

    @Query("SELECT * FROM ${CategoryEntity.CATEGORY_TABLE_NAME}")
    fun getAllCategories(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM ${CategoryEntity.CATEGORY_TABLE_NAME} WHERE id = :categoryId")
    suspend fun getCategoryById(categoryId: CategoryID): CategoryEntity

}