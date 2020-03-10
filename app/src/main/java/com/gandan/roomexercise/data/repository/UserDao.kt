package com.gandan.roomexercise.data.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gandan.roomexercise.model.User
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): Single<List<User>>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray) : Single<List<User>>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND" + " last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String) : Single<User>

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}