package com.igomezal.calculoaristachimenea.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.igomezal.calculoaristachimenea.repository.entities.Chimenea
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult

class ChimeneasRepository(application: Application) {
    private val db = AppDatabase.getInstance(application)
    private val chimeneaDAO = db.chimeneaDao()
    private val allChimeneas = chimeneaDAO.getAllChimeneas()

    fun getAllChimeneas(): LiveData<List<Chimenea>> {
        return allChimeneas
    }

    fun insertChimenea(chimenea: Chimenea): Long {
        return doAsyncResult {
            chimeneaDAO.insertChimenea(chimenea)
        }.get()
    }

    fun deleteChimenea(chimenea: Chimenea) {
        doAsync {
            chimeneaDAO.deleteChimenea(chimenea)
        }
    }
}