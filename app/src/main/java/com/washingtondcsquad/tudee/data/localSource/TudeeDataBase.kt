package com.washingtondcsquad.tudee.data.localSource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.washingtondcsquad.tudee.data.localSource.daos.CategoryDao
import com.washingtondcsquad.tudee.data.localSource.daos.TaskDao
import com.washingtondcsquad.tudee.data.localSource.model.CategoryEntity
import com.washingtondcsquad.tudee.data.localSource.model.TaskEntity
import com.washingtondcsquad.tudee.data.localSource.utils.Converter

@Database(entities = [CategoryEntity::class, TaskEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class TudeeDataBase : RoomDatabase() {
    abstract fun daoCategory(): CategoryDao
    abstract fun daoTask(): TaskDao

    companion object {
        @Volatile
        private var instance: TudeeDataBase? = null
        fun getInstance(context: Context): TudeeDataBase {
            return instance ?: synchronized(this) { buildDatabase(context).also { instance = it } }
        }

        private fun buildDatabase(context: Context): TudeeDataBase {
            return Room.databaseBuilder(
                context, TudeeDataBase::class.java, DATABASE_NAME
            ).build()
        }
        const val DATABASE_NAME = "TudeeDatabase"
    }
}

