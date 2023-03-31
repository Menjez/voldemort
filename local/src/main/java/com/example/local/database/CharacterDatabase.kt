package com.example.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.local.dao.CharacterDao
import com.example.local.database.converters.ListToStringConverter
import com.example.local.database.converters.WandToStringConverter
import com.example.local.models.CharacterEntity

@TypeConverters(ListToStringConverter::class, WandToStringConverter::class)
@Database(entities = [CharacterEntity::class], version = 1, exportSchema = true)
abstract class CharacterDatabase:RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object{
        @Volatile
        private var INSTANCE: CharacterDatabase?= null

        fun getDatabase(context: Context): CharacterDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext, CharacterDatabase::class.java,"local_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}