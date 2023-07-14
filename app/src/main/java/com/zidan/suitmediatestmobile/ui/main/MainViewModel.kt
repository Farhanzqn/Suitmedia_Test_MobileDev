package com.zidan.suitmediatestmobile.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.zidan.suitmediatestmobile.data.repository.ListRepository
import com.zidan.suitmediatestmobile.data.response.DataItem
import com.zidan.suitmediatestmobile.model.UserPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val userPreference: UserPreference,
        userListRepository: ListRepository): ViewModel(){

    val users: LiveData<PagingData<DataItem>> =
        userListRepository.getUserList().cachedIn(viewModelScope)


    fun saveSelectedUser(selectedUser: String) {
        viewModelScope. launch {
            userPreference.saveUserSelect(selectedUser)
        }
    }
}