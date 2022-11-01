package com.ci.act.widgets

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.ci.act.R
import com.google.android.material.textfield.TextInputLayout
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class CustomTextInputLayoutDatePickers : TextInputLayout {
    companion object {
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formatAPiSent = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    }

    var day: Int = 0
    var month: Int = 0
    var yearOf: Int = 0
    private var datePickerDialog: DatePickerDialog? = null

    constructor(context: Context) : super(context) {
        initWithAttrs(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        if (!isInEditMode)
            initWithAttrs(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        if (!isInEditMode)
            initWithAttrs(context, attrs, defStyleAttr)
    }

    /**
     * This method is used to set the Custom fonts
     */
    private fun initWithAttrs(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val a =
            context.theme.obtainStyledAttributes(attrs, R.styleable.CustomViews, defStyleAttr, 0)
        val customFontIndex = a.getInt(R.styleable.CustomTextView_setFont, 0)
        val fontPath = resources.getStringArray(R.array.FontNames)[customFontIndex]
        setCustomFont(fontPath)
        editText?.isCursorVisible = false
        editText?.isFocusableInTouchMode = false
        editText?.isClickable = false
        editText?.requestFocus()
        a.recycle()

    }

    /**
     * Init view
     *
     */
    fun initView() {
        editText?.setOnClickListener {
            val date = editText?.text?.toString()
            showDatePicker(date)
        }
        editText?.setCompoundDrawablesWithIntrinsicBounds(
            null,
            null,
            ContextCompat.getDrawable(context, R.drawable.ic_date),
            null
        )

    }

    /**
     * Clear edit text field
     *
     */
    fun clearEditTextField() {
        editText?.text?.clear()
    }

    /**
     * Show date picker
     *
     * @param date
     */
    private fun showDatePicker(date: String? = null) {
        val calendar = Calendar.getInstance()
        if (datePickerDialog == null) {
            if (date?.length ?: 0 > 0) {
                try {
                    val split: Array<String>? = date?.split("/")?.toTypedArray()
                    split?.let {
                        day = Integer.valueOf(it[0])
                        month = Integer.valueOf(it[1])
                        yearOf = Integer.valueOf(it[2])
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                val dateSetListener =
                    DatePickerDialog.OnDateSetListener { view, yearR, monthOfYear, dayOfMonth ->
                        calendar.set(Calendar.YEAR, yearR)
                        calendar.set(Calendar.MONTH, monthOfYear)
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        editText?.setText(format.format(calendar.time))
                    }
                datePickerDialog = DatePickerDialog(
                    context,
                    dateSetListener, yearOf, month - 1, day
                )
            } else {
                datePickerDialog = DatePickerDialog(
                    context,
                    DatePickerDialog.OnDateSetListener { arg0, year, month, day_of_month ->
                        calendar.set(Calendar.YEAR, year)
                        calendar.set(Calendar.MONTH, month)
                        calendar.set(Calendar.DAY_OF_MONTH, day_of_month)
                        editText?.setText(format.format(calendar.time))
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
            }
        }
        datePickerDialog?.datePicker?.maxDate = Date().time
        datePickerDialog?.show()
    }

    /**
     * Loads a font from the given asset path
     *
     * @param customFontPath path in the assets folder to the font file
     */
    private fun setCustomFont(customFontPath: String) {
        if (isInEditMode && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) {
            //in the Android Studio stf_template preview, with SDK < 22, this throws an exception.  You'll
            // only see your custom font in the preview if you have the SDK set to 22 or above.
            return
        }
        val typeface = Typeface.createFromAsset(context.assets, "fonts/$customFontPath")
        setTypeface(typeface)
    }
}

