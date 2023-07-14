package com.zidan.suitmediatestmobile.helper

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zidan.suitmediatestmobile.data.response.DataItem


@Database(
    entities = [DataItem::class],
    version = 1,
    exportSchema = false
)
abstract class UserDb : RoomDatabase() {
    companion object {
        @Volatile
        private var instance: UserDb? = null

        @JvmStatic
        fun getDatabase(context: Context): UserDb {
            return instance ?: synchronized(this) {
                instance ?:  Room.databaseBuilder(
                    context.applicationContext,
                    UserDb::class.java, "user_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }
    }
}