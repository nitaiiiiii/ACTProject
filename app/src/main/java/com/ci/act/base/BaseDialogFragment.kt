package com.ci.act.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.ci.act.R
import com.ci.act.data.AppDataManager

/********************************************************************************
 * @author       Tech.us Developers
 * @module       base
 * @name         BaseDialogFragment
 * @copyright    2020 - 2030 Tech.us Developers Inc
 * @createdon    10-08-2021.
 * @license      Tech.us Developers GPL - https://example.com/developer-license
 * @since        1.0
 *********************************************************************************/
open class BaseDialogFragment:DialogFragment() {

    var appDataManager: AppDataManager? = null
    init {
        appDataManager = AppDataManager.getMyInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.dialog_open_animation
    }

    override fun onStart() {
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = params as WindowManager.LayoutParams
        super.onStart()
    }

    override fun onResume() {
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = params as WindowManager.LayoutParams
        super.onResume()
    }

}