package com.codility.roomdatabase.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.codility.roomdatabase.User


@Dao
interface UserModelDao {

    @Query("select * from User")
    fun getAllUserItems(): LiveData<List<User>>

    @Query("select * from User where id = :id")
    fun getItemById(id: String): User

    @Insert(onConflict = REPLACE)
    fun addUserModel(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Insert
    fun insertAll(vararg users: User)

    @Query("delete from User")
    fun deleteAll()
}