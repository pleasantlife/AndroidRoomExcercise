package com.gandan.roomexercise.data.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gandan.roomexercise.model.User
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    //여기에서 Flowable은 complete를 수행하지 않는다!
    fun getAll(): Flowable<List<User>>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray) : Flowable<List<User>>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND" + " last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String) : Flowable<User>

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}