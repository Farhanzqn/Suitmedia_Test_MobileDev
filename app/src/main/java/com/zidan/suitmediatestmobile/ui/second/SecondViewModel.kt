package com.zidan.suitmediatestmobile.ui.second

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.zidan.suitmediatestmobile.model.UserModel
import com.zidan.suitmediatestmobile.model.UserPreference

class SecondViewModel (private val userPreference: UserPreference): ViewModel() {

    fun getGreeting(): LiveData<UserModel>{
        return userPreference.getUser().asLiveData()
    }
}