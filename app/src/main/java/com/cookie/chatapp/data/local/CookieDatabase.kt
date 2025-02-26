package com.cookie.chatapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cookie.chatapp.data.local.dao.UserDao
import com.cookie.chatapp.data.local.entities.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class CookieDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{
        private var dbInstance:CookieDatabase?=null

        fun getCookieDatabase(context:Context):CookieDatabase{
            if(dbInstance != null){
                return dbInstance!!
            }
            else{
                synchronized(this){
                    dbInstance = Room.databaseBuilder(
                        context.applicationContext,
                        CookieDatabase::class.java,
                        "cookie-database"
                    ).build()
                    return dbInstance!!
                }
            }
        }
    }
}