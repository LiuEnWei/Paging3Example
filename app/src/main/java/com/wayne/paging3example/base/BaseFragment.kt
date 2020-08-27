package com.wayne.paging3example.base

import android.app.Dialog
import android.content.DialogInterface
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.wayne.paging3example.R

abstract class BaseFragment(@LayoutRes contentLayoutId: Int): Fragment(contentLayoutId) {

    fun showErrorMessage(message: String?, okClickListener: DialogInterface.OnClickListener? = null): Dialog {
        val errorDialog = AlertDialog.Builder(requireContext())
        errorDialog.setTitle(R.string.oops)
        errorDialog.setMessage(message)
        errorDialog.setCancelable(false)
        errorDialog.setPositiveButton(android.R.string.ok,
            okClickListener?: DialogInterface.OnClickListener { dialog, _ -> dialog?.dismiss() })
        return errorDialog.show()
    }
}