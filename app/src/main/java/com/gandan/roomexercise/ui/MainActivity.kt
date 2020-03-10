package com.gandan.roomexercise.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gandan.roomexercise.R
import com.gandan.roomexercise.data.repository.AppDatabase
import com.gandan.roomexercise.model.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var db: RoomDatabase.Builder<AppDatabase>
    private val compositeDisposable = CompositeDisposable();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "test-database"
        ).addMigrations().fallbackToDestructiveMigration()


        saveName.setOnClickListener {
            saveUserData()
        }

        loadUserName.setOnClickListener { loadUserData() }

    }

    private fun saveUserData() {
        val inputUser = User(
            null,
            "android",
            "kim"
        )

        val inputUserTwo = User(
            null,
            "android",
            "park"
        )

        val inputUserThree = User(
            null,
            "android",
            "lee"
        )


        /*val observable = Observable.just(inputUser, inputUserTwo, inputUserThree).subscribeOn(Schedulers.io())
            *//*.observeOn(AndroidSchedulers.mainThread())*//*
            .doOnComplete { Log.w("Complete::", "one") }.doOnError { Log.e("ERROR!::", "${it.message}") }


        compositeDisposable.add(
            observable.subscribe {
                db.build().userDao().insertAll(it)
                //Log.w("User", "${it.lastName}")
            }
        )*/

        //compositeDisposable.dispose()


    }

    private fun loadUserData() {

        /*val observable = Observable.fromCallable {
            return@fromCallable db.build().userDao().getAll()
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnComplete {
            Log.w("Complete:::", "two")
        }.doOnError {
            Log.e("Error", "${it.message}")
        }

        compositeDisposable.add(
            observable.subscribe {
                it.doOnSuccess {
                    Log.w("Success::", "${it.size}")
                }
            }
        )*/

        //compositeDisposable.dispose()
        
    }

}
