package com.igomezal.calculoaristachimenea

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.bottomappbar.BottomAppBar
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.security.AccessControlContext

enum class States { HOME, ADD_CHIMENEA }

class MainActivity : AppCompatActivity() {
    private var exitEnabled = false
    var currentState = States.HOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.homeContainer, ListViewFragment.newInstance())
                .commit()
    }

    override fun onBackPressed() {
        when(currentState) {
            States.HOME -> {
                if(exitEnabled) {
                    super.onBackPressed()
                }
                exitEnabled = true
                Toast.makeText(this, resources.getString(R.string.exit_application), Toast.LENGTH_SHORT).show()
                Handler().postDelayed({ exitEnabled = false }, 2000)
            }
            States.ADD_CHIMENEA -> {
                bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                addCalculatedSize.setImageResource(R.drawable.ic_add_36)
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.homeContainer, ListViewFragment.newInstance())
                        .commit()
            }
        }
    }
}
