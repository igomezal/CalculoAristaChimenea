package com.igomezal.calculoaristachimenea

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Chimenea::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun chimeneaDao(): ChimeneaDAO

    companion object: SingletonHolder<AppDatabase, Context>({
        Room.databaseBuilder(it.applicationContext, AppDatabase::class.java, "calc-arista-chimenea")
                .allowMainThreadQueries()
                .build()
    })
}