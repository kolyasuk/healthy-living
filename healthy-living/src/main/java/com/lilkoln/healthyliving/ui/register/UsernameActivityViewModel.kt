package com.lilkoln.healthyliving.ui.register

import android.app.Application
import androidx.lifecycle.*
import com.lilkoln.healthyliving.db.AppDataBase
import com.lilkoln.healthyliving.db.User
import com.lilkoln.healthyliving.db.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class UsernameActivityViewModel(private val repository: UserRepository) : ViewModel() {

    val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun createUser(user: User) {
        viewModelScope.launch {
            repository.createUser(user)
            _user.value = user
        }
    }

    fun loadUser() {
        viewModelScope.launch {
            val userByEmail = repository.findUserByEmail()
            _user.value = userByEmail
        }
    }

}