package com.example.commonmanager.ui.fragment

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.commonmanager.R
import com.example.commonmanager.broadcastreceiver.ChargeReceiver
import com.example.commonmanager.databinding.FragmentDynamicBroadBinding

class DynamicBroadFragment : Fragment() {

    private lateinit var binding: FragmentDynamicBroadBinding
    private val chargeReceiver by lazy { ChargeReceiver() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDynamicBroadBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            registerBroadcast()
        }

        binding.btnUnregister.setOnClickListener {
            requireActivity().unregisterReceiver(chargeReceiver)
        }
    }

    override fun onPause() {
        requireActivity().unregisterReceiver(chargeReceiver)
        super.onPause()
    }

    private fun registerBroadcast() {
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        ContextCompat.registerReceiver(
            requireContext(),
            chargeReceiver,
            filter,
            ContextCompat.RECEIVER_EXPORTED
        )
    }

}