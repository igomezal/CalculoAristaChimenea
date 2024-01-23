package com.igomezal.calculoaristachimenea.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.igomezal.calculoaristachimenea.databinding.PrivacyPolicyBinding

class PrivacyPolicyFragment : Fragment() {
    private var _binding: PrivacyPolicyBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(): PrivacyPolicyFragment {
            return PrivacyPolicyFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = PrivacyPolicyBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.markdownViewTest.loadMarkdownFile("file:///android_asset/privacy_policy.md")
    }
}