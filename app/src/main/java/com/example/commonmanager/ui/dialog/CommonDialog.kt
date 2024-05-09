package com.example.commonmanager.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View.GONE
import android.view.ViewGroup
import com.example.commonmanager.R
import com.example.commonmanager.databinding.DialogCommonBinding

class CommonDialog : Dialog {
    interface DialogBottomClickListener {
        fun clickLeft()
        fun clickRight()
    }

    private lateinit var binding: DialogCommonBinding
    private var title: String = ""
    private var content: String = ""
    private var leftText: String = ""
    private var rightText: String = ""
    private var dialogBottomClickListener: DialogBottomClickListener? = null

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
        leftText: String?,
        rightText: String,
        dialogBottomClickListener: DialogBottomClickListener
    ) : super(context, R.style.bg_dialog) {
        // * style을 부모에 넘기지 않으면 내가 xml에 선언한대로 나오지 않음.
        this.title = title ?: ""
        this.content = content
        this.leftText = leftText ?: ""
        this.rightText = rightText
        this.dialogBottomClickListener = dialogBottomClickListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogCommonBinding.inflate(layoutInflater)
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
        leftText.run {
            if (this.isBlank()) {
                binding.tvCancel.visibility = GONE
                binding.vDivide.visibility = GONE
            } else {
                binding.tvCancel.text = this

            }
        }

        binding.tvContent.text = content
        binding.tvConfirm.text = rightText
        binding.tvConfirm.setOnClickListener {
            dismiss()
            dialogBottomClickListener?.clickRight()
        }
        binding.tvCancel.setOnClickListener {
            dismiss()
            dialogBottomClickListener?.clickLeft()
        }
    }
}