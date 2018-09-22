package com.igomezal.calculoaristachimenea.repository.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "chimeneas")
data class Chimenea(@PrimaryKey(autoGenerate = true) var id: Long, val x: Double, val y: Double, val height: Double) {
    var edge: Double

    init {
        this.edge = calcEdgeOfChimenea(x, y, height)
    }

    private fun calcEdgeOfChimenea(sideA: Double, sideB: Double, edge: Double): Double {
        return (Math.hypot(Math.hypot(sideA/2, sideB/2), edge))
    }
}