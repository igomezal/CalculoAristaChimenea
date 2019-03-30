package com.igomezal.calculoaristachimenea.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.igomezal.calculoaristachimenea.R
import kotlinx.android.synthetic.main.privacy_policy.*

class PrivacyPolicyFragment : Fragment() {
    companion object {
        fun newInstance(): PrivacyPolicyFragment {
            return PrivacyPolicyFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.privacy_policy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        markdownViewTest.loadMarkdownFile("file:///android_asset/privacy_policy.md")
    }
}