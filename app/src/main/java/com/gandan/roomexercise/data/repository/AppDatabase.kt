package com.gandan.roomexercise.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gandan.roomexercise.model.User

@Database(entities = arrayOf(User::class), version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}