package com.igomezal.calculoaristachimenea.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.igomezal.calculoaristachimenea.repository.entities.Chimenea
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.withContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult

class ChimeneasRepository(application: Application) {
    private val db = AppDatabase.getInstance(application)
    private val chimeneaDAO = db.chimeneaDao()
    private val allChimeneas = chimeneaDAO.getAllChimeneas()

    fun getAllChimeneas(): LiveData<List<Chimenea>> {
        return allChimeneas
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun insertChimenea(chimenea: Chimenea): Long {
        return GlobalScope.doAsyncResult {
            chimeneaDAO.insertChimenea(chimenea)
        }.get()
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun deleteChimenea(chimenea: Chimenea) {
        GlobalScope.doAsync {
            chimeneaDAO.deleteChimenea(chimenea)
        }
    }
}