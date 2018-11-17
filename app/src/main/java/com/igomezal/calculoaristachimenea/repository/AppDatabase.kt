package com.igomezal.calculoaristachimenea.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.igomezal.calculoaristachimenea.util.SingletonHolder
import com.igomezal.calculoaristachimenea.repository.entities.Chimenea
import com.igomezal.calculoaristachimenea.repository.entities.ChimeneaDAO

@Database(entities = [Chimenea::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun chimeneaDao(): ChimeneaDAO

    companion object: SingletonHolder<AppDatabase, Context>({
        Room.databaseBuilder(it.applicationContext, AppDatabase::class.java, "calc-arista-chimenea")
                .build()
    })
}