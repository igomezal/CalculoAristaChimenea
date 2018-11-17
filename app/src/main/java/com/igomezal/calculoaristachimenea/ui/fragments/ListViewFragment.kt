package com.igomezal.calculoaristachimenea.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.igomezal.calculoaristachimenea.R
import com.igomezal.calculoaristachimenea.ui.MainActivity
import com.igomezal.calculoaristachimenea.ui.States
import com.igomezal.calculoaristachimenea.ui.adapters.ChimeneasViewAdapter
import com.igomezal.calculoaristachimenea.viewmodel.ChimeneaViewModel

class ListViewFragment : Fragment() {
    companion object {
        fun newInstance(): Fragment {
            return ListViewFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val activity: MainActivity = this.activity as MainActivity
        val mChimeneaViewModel = ViewModelProvider(this, ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(activity.application))
                .get(ChimeneaViewModel::class.java)
        val submitActionButton = activity.findViewById<FloatingActionButton>(R.id.addCalculatedSize)
        val view = inflater.inflate(R.layout.fragment_list_view, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.chimeneaList)

        activity.currentState = States.HOME
        mChimeneaViewModel.mAllChimeneas.observe(this, Observer {
            recyclerView.adapter = ChimeneasViewAdapter(it!!)
        })
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        submitActionButton?.contentDescription = resources.getString(R.string.add_new_chimenea_navigation)
        submitActionButton?.setOnClickListener{

            activity.findViewById<BottomAppBar>(R.id.bottomAppBar)?.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
            submitActionButton.setImageResource(R.drawable.ic_done_36)
            activity.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.homeContainer, AddCalculatedSizeFragment.newInstance())
                    ?.commit()
        }

        return view
    }


}