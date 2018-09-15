package com.igomezal.calculoaristachimenea

import android.os.Bundle
import android.support.design.bottomappbar.BottomAppBar
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.add_calculated_size.*
import java.lang.Double.parseDouble

class AddCalculatedSizeFragment : Fragment() {

    companion object {
        fun newInstance(): AddCalculatedSizeFragment {
            return AddCalculatedSizeFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val activity: MainActivity = this.activity as MainActivity

        activity.findViewById<FloatingActionButton>(R.id.addCalculatedSize)?.setOnClickListener {

            val xText = etSideX.text
            val yText = etSideY.text
            val heightText = etHeight.text

            if (xText!!.isNotEmpty() && yText!!.isNotEmpty() && heightText!!.isNotEmpty()) {
                val x = parseDouble(xText.toString())
                val y = parseDouble(yText.toString())
                val height = parseDouble(heightText.toString())

                AppDatabase.getInstance(activity.applicationContext)?.chimeneaDao()!!.insertChimenea(Chimenea(0, x, y, height))
            }

            activity.findViewById<BottomAppBar>(R.id.bottomAppBar)?.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
            activity.findViewById<FloatingActionButton>(R.id.addCalculatedSize)?.setImageResource(R.drawable.ic_add_36)
            activity.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.homeContainer, ListViewFragment.newInstance())
                    ?.commit()
        }

        return inflater.inflate(R.layout.add_calculated_size, container, false)
    }
}
