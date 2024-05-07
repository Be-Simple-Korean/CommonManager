package com.example.commonmanager.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.enableSavedStateHandles
import com.example.commonmanager.R
import com.example.commonmanager.databinding.FragmentDialogKtBinding
import com.example.commonmanager.ui.dialog.CommonDialog
import com.example.commonmanager.ui.dialog.CustomBottomSheetDialog
import com.example.commonmanager.ui.dialog.SimpleBottomDialog

enum class DialogType {
    CENTER,
    BOTTOM,
    MODAL
}

class DialogKtFragment : Fragment() {

    private lateinit var binding: FragmentDialogKtBinding
    private var dialogType = DialogType.CENTER
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogKtBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_center -> {
                    dialogType = DialogType.CENTER
                }

                R.id.rb_bottom -> {
                    dialogType = DialogType.BOTTOM
                }

                R.id.rb_modal -> {
                    dialogType = DialogType.MODAL
                }
            }
        }

        binding.btnShow.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val content = binding.etValue.text.toString()
            when (dialogType) {
                DialogType.CENTER -> {
                    showCenterDialog(title, content)
                }

                DialogType.BOTTOM -> {
                    showBottomDialog(title, content)
                }

                DialogType.MODAL -> {
                    showModalBottomSheetDialog()
                }
            }
        }
    }

    /**
     * 중앙 팝업 노출
     */
    private fun showCenterDialog(title: String, content: String) {
        val dialog = CommonDialog(
            requireContext(),
            title,
            content,
            "취소",
            "확인",
            object : CommonDialog.DialogBottomClickListener {
                override fun clickLeft() {
                    Log.e("dialog", "clickLeft")
                }

                override fun clickRight() {
                    Log.e("dialog", "clickRight")
                }
            })
        dialog.setCancelable(false)
        dialog.show()
    }

    /**
     * 바텀 팝업 노출
     */
    private fun showBottomDialog(title: String, content: String) {
        val dialog = SimpleBottomDialog(
            requireContext(),
            title,
            content,
            "확인",
            object : SimpleBottomDialog.BottomDialogClickListener {
                override fun clickConfirm() {
                    Log.e("tag", "clickConfirm()")
                }

            })
        dialog.setCancelable(false)
        dialog.show()
    }

    /**
     * 바텀시트 팝업 노출
     */
    private fun showModalBottomSheetDialog(){
        val dialog = CustomBottomSheetDialog()
        dialog.isCancelable = true
        dialog.show(requireActivity().supportFragmentManager,null)
    }
}