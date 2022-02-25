package com.smartherd.mvvm_simplifiedcoding.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.smartherd.mvvm_simplifiedcoding.data.db.entities.CURRENT_USER_ID
import com.smartherd.mvvm_simplifiedcoding.data.db.entities.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user : User) : Long

    @Query("select * from User where uid = $CURRENT_USER_ID")
    fun getUser() : LiveData<User>
}