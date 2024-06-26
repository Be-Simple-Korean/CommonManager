package com.example.commonmanager.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.commonmanager.R
import com.example.commonmanager.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners();
    }

    private fun initListeners() {
        binding.btnPref.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_prefFragment)
        }

        binding.btnDialogKt.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_dialogKtFragment)
        }

        binding.btnBroadcast.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_dynamicBroadFragment)
        }
    }
}