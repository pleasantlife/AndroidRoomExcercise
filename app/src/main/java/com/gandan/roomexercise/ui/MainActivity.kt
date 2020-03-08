package com.gandan.roomexercise.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import androidx.room.migration.Migration
import com.gandan.roomexercise.R
import com.gandan.roomexercise.data.repository.AppDatabase
import com.gandan.roomexercise.model.User

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "user-database"
        ).addMigrations().build()

        val inputUser = User(
            1,
            "android",
            "kim"
        )

        val inputUserTwo = User(
            2,
            "android",
            "park"
        )

        val inputUserThree = User(
            3,
            "android",
            "lee"
        )

        db.userDao().insertAll(inputUser)

        for(user in db.userDao().getAll()){
            Log.w("userInfo", "${user.firstName} ${user.lastName}")
        }


    }
}
