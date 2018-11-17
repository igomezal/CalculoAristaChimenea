package com.igomezal.calculoaristachimenea.repository.entities

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ChimeneaDAO {
    @Query("SELECT * FROM chimeneas ORDER BY id DESC")
    fun getAllChimeneas(): LiveData<List<Chimenea>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChimenea(chimenea: Chimenea): Long

    @Update
    fun updateChimenea(chimena: Chimenea)

    @Delete
    fun deleteChimenea(chimeneas: Chimenea)
}