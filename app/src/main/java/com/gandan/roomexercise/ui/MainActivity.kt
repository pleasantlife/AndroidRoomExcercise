package com.gandan.roomexercise.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import androidx.room.migration.Migration
import com.gandan.roomexercise.R
import com.gandan.roomexercise.data.repository.AppDatabase
import com.gandan.roomexercise.data.repository.UserDao
import com.gandan.roomexercise.model.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val db = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        "user-database"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /* val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "user-database"
        ).addMigrations().build()*/




        saveName.setOnClickListener {
            saveUserData()
        }

        loadUserName.setOnClickListener{ loadUserData()  }

    }

    fun saveUserData(){
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


        val observable = Observable.just(inputUser, inputUserTwo, inputUserThree).observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread())


        val compositeDisposable = CompositeDisposable()

        compositeDisposable.add(
            observable.subscribe {
                db.build().userDao().insertAll(it)
            }
        )

        compositeDisposable.dispose();

    }

    fun loadUserData() {
        val compositeDisposable = CompositeDisposable()

        val observable = Observable.fromCallable {
            var data = db.build().userDao().getAll()
            return@fromCallable data
        }.observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread())

        compositeDisposable.add(
            observable.subscribe { userList ->
                userList.forEach {
                    Log.w("userName", "${it[0].firstName}")
                }
            }
        )
    }

}
