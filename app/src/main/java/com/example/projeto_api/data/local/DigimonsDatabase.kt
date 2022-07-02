package com.example.projeto_api.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LocalDigimon::class], version = 1)
abstract class DigimonsDatabase : RoomDatabase() {

    abstract fun digimonDao(): DigimonDao

    companion object {

        @Volatile
        private var INSTANCE: DigimonsDatabase? = null

        fun getInstance(context: Context): DigimonsDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DigimonsDatabase::class.java,
                    "digimons_database"
                )

                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }

}