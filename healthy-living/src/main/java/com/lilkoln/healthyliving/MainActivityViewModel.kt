package com.lilkoln.healthyliving

import android.app.Application
import androidx.lifecycle.*
import com.lilkoln.healthyliving.db.AppDataBase
import com.lilkoln.healthyliving.db.User
import com.lilkoln.healthyliving.db.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: UserRepository) : ViewModel() {

    val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun createUser(name: String) {
        viewModelScope.launch {
            val user = User(null, name)
            repository.createUser(user)
        }
    }

    fun loadFirstUser() {
        viewModelScope.launch {
            val userByEmail = repository.findUserByEmail()
            _user.value = userByEmail
        }
    }

}