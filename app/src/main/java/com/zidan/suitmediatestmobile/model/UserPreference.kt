package com.zidan.suitmediatestmobile.model

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {

    fun getUser(): Flow<UserModel>{
        return dataStore.data.map { pref ->
            UserModel(
                pref[USER_KEY] ?: "",
                pref[SELECTED_USER_KEY] ?: "Selected User First",
            )
        }
    }

    suspend fun saveUser(userList: String){
        dataStore.edit { pref ->
            pref[USER_KEY] = userList
        }
    }

    suspend fun saveUserSelect(selectUser: String){
        dataStore.edit { pref ->
            pref[SELECTED_USER_KEY] = selectUser
        }
    }
    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        private val USER_KEY = stringPreferencesKey("user")
        private val SELECTED_USER_KEY = stringPreferencesKey("selected_user")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}