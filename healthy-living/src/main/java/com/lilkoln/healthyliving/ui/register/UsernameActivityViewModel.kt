package com.lilkoln.healthyliving.ui.register

import androidx.lifecycle.*
import com.lilkoln.healthyliving.db.entity.User
import com.lilkoln.healthyliving.db.repository.UserRepository
import kotlinx.coroutines.launch

class UsernameActivityViewModel(private val userRepository: UserRepository) : ViewModel() {

    val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun createUser(user: User) {
        viewModelScope.launch {
            userRepository.createUser(user)
            _user.value = user
        }
    }

    fun loadUser() {
        viewModelScope.launch {
            val userByEmail = userRepository.findUserByEmail()
            _user.value = userByEmail
        }
    }

}