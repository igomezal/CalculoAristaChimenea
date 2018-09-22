package com.igomezal.calculoaristachimenea.repository.entities

import android.arch.persistence.room.*

@Dao
interface ChimeneaDAO {
    @Query("SELECT * FROM chimeneas ORDER BY id DESC")
    fun getAllChimeneas(): List<Chimenea>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChimenea(chimenea: Chimenea): Long

    @Update
    fun updateChimenea(chimena: Chimenea)

    @Delete
    fun deleteChimenea(chimeneas: Chimenea)
}