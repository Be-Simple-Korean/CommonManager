package com.example.commonmanager.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.commonmanager.R
import com.example.commonmanager.databinding.FragmentDialogKtBinding
import com.example.commonmanager.ui.dialog.CommonDialog


class DialogKtFragment : Fragment() {
    private lateinit var binding: FragmentDialogKtBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogKtBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnShow.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val content = binding.etValue.text.toString()
            val dialog = CommonDialog(requireContext(), title, content, "취소", "확인",object : CommonDialog.DialogBottomClickListener{
                override fun clickLeft() {
                    Log.e("dialog","clickLeft")
                }

                override fun clickRight() {
                    Log.e("dialog","clickRight")
                }
            })
            dialog.setCancelable(false)
            dialog.show()
        }
    }
}