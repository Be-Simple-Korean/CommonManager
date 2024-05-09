package com.example.commonmanager.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import com.example.commonmanager.R
import com.example.commonmanager.databinding.FragmentPrefBinding
import com.example.commonmanager.utils.PrefManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PrefFragment : Fragment() {

    private lateinit var binding: FragmentPrefBinding

    @Inject
    lateinit var prefManager: PrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this){
            Log.e("뒤로가기","pref")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrefBinding.inflate(layoutInflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners();
    }

    private fun initListeners() {
        binding.btnPut.setOnClickListener {
            val key = binding.etKey.text.toString();
            val value = binding.etValue.text.toString();
            if (key.isEmpty() || value.isEmpty()) {
                Toast.makeText(context, "값을 제대로 입력해주세요", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            prefManager.setValue(key, value);
            binding.etValue.setText("");
            Toast.makeText(context, "성공", Toast.LENGTH_SHORT)
                .show()
        }

        binding.btnGet.setOnClickListener {
            val key = binding.etKey.text.toString();
            if (key.isEmpty()) {
                Toast.makeText(context, "값을 제대로 입력해주세요", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            var value = prefManager.getString(key);
            Toast.makeText(context, "value >> $value", Toast.LENGTH_SHORT)
                .show()
        }
    }
}