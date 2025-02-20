package com.cookie.chatapp.di

import android.content.Context
import com.cookie.chatapp.data.manager.PreferencesManagerImpl
import com.cookie.chatapp.domain.manager.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ManagerModule {

    @Provides
    @Singleton
    fun providePreferencesManager(@ApplicationContext context: Context): PreferenceManager{
        return PreferencesManagerImpl(context)
    }
}