package com.cookie.chatapp.di

import com.cookie.chatapp.data.remote.AllRoomApi
import com.cookie.chatapp.data.remote.RoomApi
import com.cookie.chatapp.data.remote.UserApi
import com.cookie.chatapp.data.remote.interceptor.IdTokenInterceptor
import com.cookie.chatapp.domain.util.json
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideIdTokenInterceptor(): IdTokenInterceptor{
        return IdTokenInterceptor()
    }

    @Provides
    @Singleton
    fun provideHttpClient(idTokenInterceptor: IdTokenInterceptor): OkHttpClient{
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(idTokenInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(client: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://192.168.0.182:8080/api/v1/")
            .client(client)
            .addConverterFactory(
                json.asConverterFactory(
                    "application/json; charset=utf-8".toMediaType()
                )
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApi{
        return retrofit.create()
    }

    @Provides
    @Singleton
    fun provideAllRoomApi(retrofit: Retrofit): AllRoomApi{
        return retrofit.create()
    }

    @Provides
    @Singleton
    fun provideRoomApi(retrofit: Retrofit): RoomApi{
        return retrofit.create()
    }
}