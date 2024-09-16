package com.grandmaskitchen.myrecipes.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import com.google.android.material.animation.AnimatableView.Listener
import com.grandmaskitchen.myrecipes.R

class DeleteConfirmationDialog: DialogFragment() {

    private var positiveButtonClick: (() -> Unit)? = null

    fun setPositiveButtonClickListener(listener: () -> Unit) {
        positiveButtonClick = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.alert_delete_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val okBtn = view.findViewById<ImageButton>(R.id.ok_btn)
        val notBtn = view.findViewById<ImageButton>(R.id.not_btn)

        okBtn.setOnClickListener {
            positiveButtonClick?.invoke()
            dismiss()
        }

        notBtn.setOnClickListener {
            dismiss()
        }
    }
}