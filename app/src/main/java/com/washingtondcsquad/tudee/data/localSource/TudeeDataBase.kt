package com.washingtondcsquad.tudee.data.localSource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.washingtondcsquad.tudee.data.localSource.entities.CategoryEntity
import com.washingtondcsquad.tudee.data.localSource.entities.TaskEntity
import com.washingtondcsquad.tudee.data.utils.DataBaseConstants

@Database(entities = [CategoryEntity::class, TaskEntity::class], version = 1, exportSchema = false)
abstract class TudeeDataBase : RoomDatabase() {
    abstract fun daoCategory(): DaoCategory
    abstract fun daoTask(): DaoTask
    companion object {
        @Volatile
        private var instance: TudeeDataBase? = null
        fun getInstance(context: Context): TudeeDataBase {
            instance =   instance ?: synchronized(this) { buildDatabase(context).also { instance = it } }
            instance?.openHelper?.writableDatabase
            return instance!!
        }

        private fun buildDatabase(context: Context): TudeeDataBase {
            return Room.databaseBuilder(
                context,
                TudeeDataBase::class.java,
                DataBaseConstants.ROOM_DATABASE_NAME
            ).build()

        }

    }
}

