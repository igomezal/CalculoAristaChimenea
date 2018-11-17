package com.igomezal.calculoaristachimenea.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.igomezal.calculoaristachimenea.repository.ChimeneasRepository
import com.igomezal.calculoaristachimenea.repository.entities.Chimenea

class ChimeneaViewModel(application: Application): AndroidViewModel(application) {
    private val mRepository = ChimeneasRepository(application)
    val mAllChimeneas = mRepository.getAllChimeneas()

    fun insert(chimenea: Chimenea): Long {
        return mRepository.insertChimenea(chimenea)
    }

    fun delete(chimenea: Chimenea) {
        mRepository.deleteChimenea(chimenea)
    }
}