package com.zidan.suitmediatestmobile.ui.first

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zidan.suitmediatestmobile.model.UserPreference
import kotlinx.coroutines.launch

class FirstViewModel(private val userPreference: UserPreference): ViewModel() {

    fun getUser(user: String){
        viewModelScope.launch {
            userPreference.saveUser(user)
        }
    }

    fun palindrome(checked: String): Boolean{
        var cleanedWord = ""
        for (char in  checked){
            if (char.isLetterOrDigit()){
                cleanedWord += char.lowercaseChar()
            }
        }

        val reverse = cleanedWord.reversed()

        return cleanedWord == reverse
    }
}