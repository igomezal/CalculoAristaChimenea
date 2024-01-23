package com.igomezal.calculoaristachimenea.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.igomezal.calculoaristachimenea.R
import com.igomezal.calculoaristachimenea.databinding.FragmentBottomsheetMenuBinding
import com.igomezal.calculoaristachimenea.ui.MainActivity

class BottomSheetMenu: BottomSheetDialogFragment() {
    private var _binding: FragmentBottomsheetMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentBottomsheetMenuBinding.inflate(inflater, container, false)

        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.navigationView.setNavigationItemSelectedListener {
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