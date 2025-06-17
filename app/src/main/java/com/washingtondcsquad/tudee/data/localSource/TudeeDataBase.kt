package com.washingtondcsquad.tudee.data.localSource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.washingtondcsquad.tudee.data.localSource.entities.CategoryEntity
import com.washingtondcsquad.tudee.data.utils.Constants

@Database(entities = [CategoryEntity::class], version = 1, exportSchema = false)
abstract class TudeeDataBase : RoomDatabase() {
    abstract fun tudeeDaoCategory(): TudeeDaoCategory

    companion object {
        @Volatile
        private var instance: TudeeDataBase? = null
        fun getInstance(context: Context): TudeeDataBase {
            return instance ?: synchronized(this) { buildDatabase(context).also { instance = it } }
        }

        private fun buildDatabase(context: Context): TudeeDataBase {
            return Room.databaseBuilder(
                context,
                TudeeDataBase::class.java,
                Constants.ROOM_DATABASE_NAME
            ).build()

        }

    }
}