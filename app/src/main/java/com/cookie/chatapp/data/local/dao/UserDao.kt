package com.cookie.chatapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cookie.chatapp.data.local.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(userEntity: UserEntity)

    @Query("SELECT * FROM userentity WHERE userId = :userId")
    suspend fun getUserById(userId: String): UserEntity

    @Query("SELECT idToken FROM userentity WHERE userId = :userId")
    suspend fun getIdToken(userId: String): String

    @Query("SELECT username FROM userentity WHERE userId = :userId")
    suspend fun getUsername(userId: String): String
}