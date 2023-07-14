package com.zidan.suitmediatestmobile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zidan.suitmediatestmobile.di.Constant
import com.zidan.suitmediatestmobile.model.UserPreference
import com.zidan.suitmediatestmobile.ui.first.FirstViewModel
import com.zidan.suitmediatestmobile.ui.main.MainViewModel
import com.zidan.suitmediatestmobile.ui.second.SecondViewModel

class ViewModelFactory(
   private val userPreference: UserPreference,
   private val context: Context
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FirstViewModel::class.java) -> {
                FirstViewModel(userPreference) as T
            }
            modelClass.isAssignableFrom(SecondViewModel::class.java) -> {
                SecondViewModel(userPreference) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(userPreference, Constant.getRepository(context)) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}