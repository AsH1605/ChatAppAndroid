package com.cookie.chatapp.di

import android.content.Context
import com.cookie.chatapp.data.local.CookieDatabase
import com.cookie.chatapp.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent:: class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCookieDatabase(@ApplicationContext context: Context): CookieDatabase{
        return CookieDatabase.getCookieDatabase(context)
    }

    @Provides
    @Singleton
    fun provideUserDatabase(database: CookieDatabase): UserDao{
        return database.userDao()
    }
}