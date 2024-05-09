package com.example.commonmanager.ui.fragment

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.AlarmManagerCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.commonmanager.broadcastreceiver.AlaramReceiver
import com.example.commonmanager.broadcastreceiver.ChargeReceiver
import com.example.commonmanager.databinding.FragmentDynamicBroadBinding
import com.example.commonmanager.utils.getCurrentTime


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

        binding.btnAlarm.setOnClickListener {
            val alaramManager =
                requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (alaramManager.canScheduleExactAlarms()) {
                    setAlaram(alaramManager)
                } else {
                    requireActivity().startActivity(Intent(
                        Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM,
                        Uri.parse("package:${requireActivity().applicationContext.applicationInfo.packageName}")
                    ).apply {
                            addCategory(Intent.CATEGORY_DEFAULT)
                            setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        })
                }
            } else {
                setAlaram(alaramManager)
            }

        }
    }

    override fun onPause() {
        requireActivity().unregisterReceiver(chargeReceiver)
        super.onPause()
    }

    private fun setAlaram(alaramManager: AlarmManager) {
        val intent = Intent(requireContext(), AlaramReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(
                requireContext(), 0, intent,
                PendingIntent.FLAG_IMMUTABLE
            )
        alaramManager.setExactAndAllowWhileIdle( // 도즈모드 대응
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + 5000,
            pendingIntent
        )
        Log.e("set", getCurrentTime())
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