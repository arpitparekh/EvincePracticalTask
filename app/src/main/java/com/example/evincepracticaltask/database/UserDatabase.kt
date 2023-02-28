package com.example.evincepracticaltask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.evincepracticaltask.dao.UserDao
import com.example.evincepracticaltask.data_classes.DataItem

@Database(
    entities = [DataItem::class],
    version = 1,
    exportSchema = false
)

abstract class UserDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object {
        private const val DB_NAME = "userDatabase.db"
        @Volatile private var instance: UserDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            UserDatabase::class.java,
            DB_NAME
        ).allowMainThreadQueries().build()
    }
}