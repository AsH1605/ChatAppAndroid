package com.cookie.chatapp.data.repository

import com.cookie.chatapp.data.local.dao.UserDao
import com.cookie.chatapp.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDao: UserDao
): UserRepository {
    override suspend fun registerUser(usename: String, password) {
        userDao.addUser()
    }
}