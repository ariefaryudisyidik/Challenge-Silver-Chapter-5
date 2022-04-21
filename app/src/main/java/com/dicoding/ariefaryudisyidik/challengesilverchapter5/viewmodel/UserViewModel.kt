package com.dicoding.ariefaryudisyidik.challengesilverchapter5.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.ariefaryudisyidik.challengesilverchapter5.database.User
import com.dicoding.ariefaryudisyidik.challengesilverchapter5.database.UserRoomDatabase
import com.dicoding.ariefaryudisyidik.challengesilverchapter5.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository

    init {
        val userDao = UserRoomDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    fun insert(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(user)
        }
    }
}