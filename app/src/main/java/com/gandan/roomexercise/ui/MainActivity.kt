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

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /* val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "user-database"
        ).addMigrations().build()*/

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

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "user-database"
        ).build();


        val compositeDisposable = CompositeDisposable();

        compositeDisposable.add(
            Single.just(db).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
                    db -> db.userDao().insertAll(inputUser, inputUserTwo, inputUserThree)
            }, {error -> Log.e("Error", "${error.message}")})
        )

        compositeDisposable.dispose()



        compositeDisposable.add(Flowable.fromCallable{
            return@fromCallable db.userDao().getAll()
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
                success -> success.doOnNext {
            for(user in it){
                Log.w("user", "${user.lastName}+${user.firstName}")
            }
        }
        }, {error -> Log.e("Error2", "${error.message}")}))


        compositeDisposable.dispose()

    }

}
