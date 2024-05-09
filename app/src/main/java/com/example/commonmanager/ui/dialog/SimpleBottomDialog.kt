package com.example.commonmanager.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View.GONE
import android.view.Window
import com.example.commonmanager.R
import com.example.commonmanager.databinding.DialogSimpleBottomBinding

class SimpleBottomDialog : Dialog {
    interface BottomDialogClickListener {
        fun clickConfirm()
    }

    private lateinit var binding: DialogSimpleBottomBinding
    private var title: String = ""
    private var content: String = ""
    private var confirmText: String = ""
    private var confirmClickListener: BottomDialogClickListener? = null

    // 첫 번째 생성자: 기본적인 Context만 받음
    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, themeResId: Int) : super(context, themeResId) {
        // 추가 초기화 코드는 여기에 작성
    }

    // 세 번째 생성자: Context, 테마, 불리언 값(예: 취소 가능 여부)
    constructor(context: Context, themeResId: Int, isCancelable: Boolean) : super(
        context,
        themeResId
    ) {
        setCancelable(isCancelable)
    }

    constructor(
        context: Context,
        title: String?,
        content: String,
        confirmText: String,
        dialogBottomClickListener: BottomDialogClickListener
    ) : super(context, R.style.bg_dialog) {
        // * style을 부모에 넘기지 않으면 내가 xml에 선언한대로 나오지 않음.
        this.title = title ?: ""
        this.content = content
        this.confirmText = confirmText
        this.confirmClickListener = dialogBottomClickListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        window?.attributes?.windowAnimations = R.style.BottomDialogAnimation

        binding = DialogSimpleBottomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        title.run {
            if (this.isBlank()) {
                binding.tvTitle.visibility = GONE
            } else {
                binding.tvTitle.text = this
            }
        }

        binding.tvContent.text = content
        binding.btnConfirm.text = confirmText
        binding.btnConfirm.setOnClickListener {
            dismiss()
            confirmClickListener?.clickConfirm()
        }
        binding.ivClose.setOnClickListener {
            dismiss()
        }
    }
}