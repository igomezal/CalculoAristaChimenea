package com.igomezal.calculoaristachimenea.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import com.google.android.material.bottomappbar.BottomAppBar
import android.widget.Toast
import com.igomezal.calculoaristachimenea.R
import com.igomezal.calculoaristachimenea.ui.fragments.BottomSheetMenu
import com.igomezal.calculoaristachimenea.ui.fragments.ListViewFragment
import kotlinx.android.synthetic.main.activity_main.*

enum class States { HOME, ADD_CHIMENEA }

class MainActivity : AppCompatActivity() {
    private var exitEnabled = false
    var currentState = States.HOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(bottomAppBar)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.homeContainer, ListViewFragment.newInstance())
                .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                val bottomNavDrawerFragment = BottomSheetMenu()
                bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
            }
        }
        return true
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
