package com.cookie.chatapp.di

import com.cookie.chatapp.data.local.dao.UserDao
import com.cookie.chatapp.data.remote.AllRoomApi
import com.cookie.chatapp.data.remote.UserApi
import com.cookie.chatapp.data.repository.AllRoomRepositoryImpl
import com.cookie.chatapp.data.repository.UserRepositoryImpl
import com.cookie.chatapp.domain.manager.PreferenceManager
import com.cookie.chatapp.domain.repository.AllRoomRepository
import com.cookie.chatapp.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        userDao: UserDao,
        userApi: UserApi,
        preferenceManager: PreferenceManager
    ): UserRepository{
        return UserRepositoryImpl(
            userDao = userDao,
            userApi = userApi,
            preferenceManager = preferenceManager
        )
    }

    @Provides
    @Singleton
    fun provideAllRoomRepository(
        userDao: UserDao,
        allRoomApi: AllRoomApi,
        preferenceManager: PreferenceManager
    ): AllRoomRepository{
        return AllRoomRepositoryImpl(
            allRoomApi = allRoomApi,
            userDao = userDao,
            preferenceManager = preferenceManager
        )
    }
}