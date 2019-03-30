package com.igomezal.calculoaristachimenea.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.igomezal.calculoaristachimenea.R
import com.igomezal.calculoaristachimenea.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_bottomsheet_menu.*

class BottomSheetMenu: BottomSheetDialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottomsheet_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navigation_view.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home_view -> {
                    fragmentManager?.beginTransaction()?.replace(R.id.homeContainer, ListViewFragment.newInstance())?.commit()
                    val activity: MainActivity = this.activity as MainActivity
                    val floatingActionButton = activity.findViewById<FloatingActionButton>(R.id.addCalculatedSize)
                    floatingActionButton?.show()
                    dismiss()
                }
                R.id.privacy_policy -> {
                    fragmentManager?.beginTransaction()?.replace(R.id.homeContainer, PrivacyPolicyFragment.newInstance())?.commit()
                    val activity: MainActivity = this.activity as MainActivity
                    val floatingActionButton = activity.findViewById<FloatingActionButton>(R.id.addCalculatedSize)
                    floatingActionButton?.hide()
                    dismiss()
                }
            }
            true

        }
    }
}