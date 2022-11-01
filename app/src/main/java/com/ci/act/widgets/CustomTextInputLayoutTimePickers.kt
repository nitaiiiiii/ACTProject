package com.ci.act.widgets

import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.ci.act.R
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*


class CustomTextInputLayoutTimePickers : TextInputLayout {

    private val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
    private var timePickerDialog: TimePickerDialog? = null

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
            showTimePicker()
        }
        editText?.setCompoundDrawablesWithIntrinsicBounds(
            null,
            null,
            ContextCompat.getDrawable(context, R.drawable.ic_arrow_drop_down),
            null
        )
    }

    /**
     * Show time picker
     *
     */
    private fun showTimePicker() {
        val c = Calendar.getInstance()
        val mHour = c.get(Calendar.HOUR_OF_DAY)
        val mMinute = c.get(Calendar.MINUTE)
        if (timePickerDialog == null) {
            timePickerDialog = TimePickerDialog(
                context,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    c.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    c.set(Calendar.MINUTE, minute)
                    editText?.setText(format.format(c.time))
                },
                mHour,
                mMinute,
                false
            )
        }
        timePickerDialog?.show()
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

