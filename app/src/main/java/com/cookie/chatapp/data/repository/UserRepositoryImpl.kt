package com.cookie.chatapp.data.repository

import android.util.Log
import com.cookie.chatapp.data.local.dao.UserDao
import com.cookie.chatapp.data.local.entities.UserEntity
import com.cookie.chatapp.data.remote.UserApi
import com.cookie.chatapp.data.remote.dto.user.UserLoginRequest
import com.cookie.chatapp.data.remote.dto.user.UserRegisterRequest
import com.cookie.chatapp.domain.manager.PreferenceManager
import com.cookie.chatapp.domain.models.UserModel
import com.cookie.chatapp.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class UserRepositoryImpl(
    private val userDao: UserDao,
    private val userApi: UserApi,
    private val preferenceManager: PreferenceManager,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): UserRepository {

    override suspend fun registerUser(user: UserModel):Boolean= withContext(ioDispatcher) {
        try {
            val request = UserRegisterRequest(
                user.username,
                firstName = user.firstName,
                lastName = user.lastName,
                email = user.email,
                password = user.password,
            )
            userApi.registerUser(request)
            true
        }catch (e: HttpException){
            Log.e("Impl", e.message())
            false
        }
    }

    override suspend fun loginUser(username: String, password: String): Boolean = withContext(ioDispatcher){
        try {
            val request = UserLoginRequest(
                username = username,
                password = password
            )
            val response = userApi.loginUser(request)
            if(response.success){
                userDao.addUser(UserEntity(
                    userId = response.data.user.id,
                    username = response.data.user.username,
                    idToken = response.idToken
                ))
                preferenceManager.setLoggedInWorker(response.data.user.id)
                true
            }
            else{
                false
            }
//            Log.d("Impl", response.data.user.id)
        }catch (e: HttpException){
            Log.e("Impl", e.message())
            false
        }
    }

    override suspend fun getLoggedInUserId(): String? {
        return withContext(
            context = ioDispatcher,
            block = {
                preferenceManager.getLoggedInUserId()
            }
        )
    }

    override suspend fun logoutUser() = withContext(ioDispatcher){
        preferenceManager.setLoggedInWorker(null)
    }

    override suspend fun getUsername(): String {
        val userId = preferenceManager.getLoggedInUserId()!!
        Log.d("UserId", userId)
        return userDao.getUsername(userId)
    }
}