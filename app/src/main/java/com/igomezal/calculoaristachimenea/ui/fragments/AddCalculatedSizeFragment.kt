package com.igomezal.calculoaristachimenea.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.igomezal.calculoaristachimenea.R
import com.igomezal.calculoaristachimenea.repository.entities.Chimenea
import com.igomezal.calculoaristachimenea.ui.MainActivity
import com.igomezal.calculoaristachimenea.ui.States
import com.igomezal.calculoaristachimenea.viewmodel.ChimeneaViewModel
import kotlinx.android.synthetic.main.add_calculated_size.*
import org.jetbrains.anko.find
import java.lang.Double.parseDouble

class AddCalculatedSizeFragment : Fragment() {

    companion object {
        fun newInstance(): AddCalculatedSizeFragment {
            return AddCalculatedSizeFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val activity: MainActivity = this.activity as MainActivity
        val mChimeneaViewModel = ViewModelProvider(this, ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(activity.application))
                .get(ChimeneaViewModel::class.java)
        val submitActionButton = activity.findViewById<FloatingActionButton>(R.id.addCalculatedSize)

        activity.currentState = States.ADD_CHIMENEA
        activity.findViewById<BottomAppBar>(R.id.bottomAppBar)?.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END

        with(submitActionButton) {
            setImageResource(R.drawable.ic_done_36)
            hide()
            show()
            contentDescription = resources.getString(R.string.button_submit_text)
            setOnClickListener { submitButton ->
                val xText = etSideX.text
                val yText = etSideY.text
                val heightText = etHeight.text

                if (xText!!.isNotEmpty() && yText!!.isNotEmpty() && heightText!!.isNotEmpty()) {
                    val x = parseDouble(xText.toString())
                    val y = parseDouble(yText.toString())
                    val height = parseDouble(heightText.toString())
                    val chimeneaToBeSaved = Chimenea(0, x, y, height)
                    val snack = Snackbar.make(activity.findViewById(R.id.activityMainId),
                            """Guardada nueva chimenea con arista de valor: ${"%.2f".format(chimeneaToBeSaved.edge)}""",
                            Snackbar.LENGTH_LONG)
                    val removedLastChimeneaSnack = Snackbar.make(activity.find(R.id.activityMainId),
                            "Se ha eliminado la última chimenea agregada",
                            Snackbar.LENGTH_LONG)

                    val chimeneaId = mChimeneaViewModel.insert(chimeneaToBeSaved)

                    snack.setAction(resources.getString(R.string.undo)) {
                        chimeneaToBeSaved.id = chimeneaId
                        mChimeneaViewModel.delete(chimeneaToBeSaved)
                        removedLastChimeneaSnack.show()
                    }

                    removedLastChimeneaSnack.config(context!!)
                    snack.config(context!!)
                    snack.show()
                }

                activity.supportFragmentManager
                        ?.beginTransaction()
                        ?.replace(R.id.homeContainer, ListViewFragment.newInstance())
                        ?.commit()
            }
        }

        return inflater.inflate(R.layout.add_calculated_size, container, false)
    }


    private fun Snackbar.config(context: Context) {
        val params = this.view.layoutParams as ViewGroup.MarginLayoutParams

        params.setMargins(48,48,48, 325)

        this.view.layoutParams = params
        this.view.background = ContextCompat.getDrawable(context, R.drawable.round_snackbar)
        this.setActionTextColor(resources.getColor(R.color.undoAction))

        ViewCompat.setElevation(this.view, 6f)
    }
}
