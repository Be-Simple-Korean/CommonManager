package com.example.commonmanager.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.commonmanager.R
import com.example.commonmanager.databinding.FragmentPrefBinding

class PrefFragment : Fragment() {

    private lateinit var binding: FragmentPrefBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrefBinding.inflate(layoutInflater,container,false);
        return binding.root;
    }
}