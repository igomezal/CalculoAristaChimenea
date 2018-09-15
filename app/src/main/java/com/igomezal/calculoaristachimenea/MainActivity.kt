package com.igomezal.calculoaristachimenea

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.homeContainer, ListViewFragment.newInstance())
                .commit()
    }
}
