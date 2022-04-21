package com.dicoding.ariefaryudisyidik.challengesilverchapter5.repository

import com.dicoding.ariefaryudisyidik.challengesilverchapter5.database.User
import com.dicoding.ariefaryudisyidik.challengesilverchapter5.database.UserDao

class UserRepository(private val userDao: UserDao) {
    fun insert(user: User) {
        userDao.insert(user)
    }

    fun checkUser(email: String, password: String) {
        userDao.checkUser(email, password)
    }
}