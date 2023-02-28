package com.example.evincepracticaltask.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.evincepracticaltask.data_classes.DataItem

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertUser(user: DataItem)

    @Update
     fun updateUser(user: DataItem)

    @Delete
     fun deleteUser(user: DataItem)

    @Query("SELECT * FROM DataItem")
    fun getAllUsers(): LiveData<List<DataItem>>

    @Query("SELECT EXISTS(SELECT * FROM DataItem WHERE id = :id)")
    fun isRowIsExist(id : Int) : LiveData<Boolean>

}