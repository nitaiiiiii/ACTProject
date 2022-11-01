package com.ci.act.widgets.toggleButtonKotlin

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ci.act.R
import com.ci.act.widgets.CustomTextView


class MultiStateToggleButton : ToggleButton {
    /**
     * A list of rendered buttons. Used to get state, among others
     */
    private var buttons: MutableList<CustomTextView>? = null
    /**
     * @return An array of the buttons' text
     */
    /**
     * The specified texts
     */
    private var customSpinnerDialogModelList: ArrayList<CustomSpinnerDialogModel?>? = null

    /**
     * The layout containing all buttons
     */
    private var mainLayout: LinearLayout? = null

    constructor(context: Context) : super(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.MultiStateToggleButton, 0, 0)
        try {
            colorPressed = a.getColor(R.styleable.MultiStateToggleButton_mstbPrimaryColor, 0)
            colorNotPressed = a.getColor(R.styleable.MultiStateToggleButton_mstbSecondaryColor, 0)
            colorPressedText =
                a.getColor(R.styleable.MultiStateToggleButton_mstbColorPressedText, 0)
            colorPressedBackground =
                a.getColor(R.styleable.MultiStateToggleButton_mstbColorPressedBackground, 0)
            pressedBackgroundResource = a.getResourceId(
                R.styleable.MultiStateToggleButton_mstbColorPressedBackgroundResource,
                0
            )
            colorNotPressedText =
                a.getColor(R.styleable.MultiStateToggleButton_mstbColorNotPressedText, 0)
            colorNotPressedBackground =
                a.getColor(R.styleable.MultiStateToggleButton_mstbColorNotPressedBackground, 0)
            notPressedBackgroundResource = a.getResourceId(
                R.styleable.MultiStateToggleButton_mstbColorNotPressedBackgroundResource,
                0
            )
        } finally {
            a.recycle()
        }
    }

    /**
     * Set the enabled state of this MultiStateToggleButton, including all of its child buttons.
     *
     * @param enabled True if this view is enabled, false otherwise.
     */
    override fun setEnabled(enabled: Boolean) {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.isEnabled = enabled
        }
    }

    /**
     * Set multiple buttons with the specified texts and default
     * initial values. Initial states are allowed, but both
     * arrays must be of the same size.
     *
     * @param customSpinnerDialogModelList An arraylist of CustomSpinnerDialogModel for the buttons
     */
    fun setElements(
        customSpinnerDialogModelList: ArrayList<CustomSpinnerDialogModel?>?
    ) {
        this.customSpinnerDialogModelList = customSpinnerDialogModelList
        val textCount = customSpinnerDialogModelList?.size ?: 0
        if (textCount == 0) {
            return
        }
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if (mainLayout == null) {
            mainLayout = inflater.inflate(
                R.layout.view_multi_state_toggle_button,
                this,
                true
            ) as LinearLayout
        }
        mainLayout?.removeAllViews()
        buttons = ArrayList(textCount)
        customSpinnerDialogModelList?.forEachIndexed { index, customSpinnerDialogModel ->
            val b: CustomTextView
            var buttonPosition = ""
            if (index == 0) {
                if (textCount == 1) {
                    b = inflater.inflate(
                        R.layout.view_single_toggle_button,
                        mainLayout,
                        false
                    ) as CustomTextView
                    buttonPosition = BUTTON_SINGLE_STATE
                } else {
                    b = inflater.inflate(
                        R.layout.view_left_toggle_button,
                        mainLayout,
                        false
                    ) as CustomTextView
                    buttonPosition = BUTTON_LEFT_STATE
                }
            } else if (index == textCount - 1) {
                b = inflater.inflate(
                    R.layout.view_right_toggle_button,
                    mainLayout,
                    false
                ) as CustomTextView
                buttonPosition = BUTTON_RIGHT_STATE
            } else {
                b = inflater.inflate(
                    R.layout.view_center_toggle_button,
                    mainLayout,
                    false
                ) as CustomTextView
                buttonPosition = BUTTON_CENTER_STATE
            }

            b.text = customSpinnerDialogModel?.value
            b.tag = customSpinnerDialogModel?.key


            b.setOnClickListener { setValue(index) }
            mainLayout?.addView(b)
            setButtonState(b, customSpinnerDialogModel?.isSelected ?: false, buttonPosition)
            buttons?.add(b)
        }
    }

    override fun setValue(value: Int) {
        for (i in buttons?.indices!!) {
            if (i == value) {
                setButtonStatePosition(buttons?.get(i), buttons?.get(i)?.isSelected != true, i)
            } else {
                setButtonStatePosition(buttons?.get(i), false, i)
            }
        }
        super.setValue(value)
    }

    private fun setButtonStatePosition(button: CustomTextView?, selected: Boolean, i: Int) {
        if (i == 0) {
            if (buttons!!.size == 1) {
                setButtonState(
                    button,
                    selected,
                    BUTTON_SINGLE_STATE
                )
            } else {
                setButtonState(button, selected, BUTTON_LEFT_STATE)
            }
        } else if (i == (buttons?.size ?: 0) - 1) {
            setButtonState(button, selected, BUTTON_RIGHT_STATE)
        } else {
            setButtonState(button, selected, BUTTON_CENTER_STATE)
        }
    }

    private fun setButtonState(button: CustomTextView?, selected: Boolean, state: String?) {
        if (button == null) {
            return
        }
        button.isSelected = selected
        when (state) {
            BUTTON_LEFT_STATE -> {
                button.setBackgroundResource(if (selected) R.drawable.bg_tgl_left_selected else R.drawable.bg_tgl_left_un_selected)
            }
            BUTTON_CENTER_STATE -> {
                button.setBackgroundResource(if (selected) R.drawable.bg_tgl_center_selected else R.drawable.bg_tgl_center_un_selected)
            }
            BUTTON_RIGHT_STATE -> {
                button.setBackgroundResource(if (selected) R.drawable.bg_tgl_right_selected else R.drawable.bg_tgl_right_un_selected)
            }
            else -> {
                button.setBackgroundResource(if (selected) R.drawable.bg_tgl_single_selected else R.drawable.bg_tgl_single_un_selected)
            }
        }
        if (colorPressed != 0 || colorNotPressed != 0) {
            button.setTextColor(if (!selected) colorPressed else colorNotPressed)
        }
        if (colorPressedText != 0 || colorNotPressedText != 0) {
            button.setTextColor(if (selected) colorPressedText else colorNotPressedText)
        }
        if (pressedBackgroundResource != 0 || notPressedBackgroundResource != 0) {
            button.setBackgroundResource(if (selected) pressedBackgroundResource else notPressedBackgroundResource)
        }
    }

    fun getValue(): Int {
        for (i in buttons!!.indices) {
            if (buttons?.get(i)?.isSelected == true) {
                return i
            }
        }
        return -1
    }


    fun refresh() {
        if (buttons?.isNullOrEmpty() == false) {
            for (i in buttons?.indices!!) {
                setButtonStatePosition(buttons?.get(i), false, i)
            }
        }
    }

    fun getSelectedKey(): String? {
        return buttons?.find { it.isSelected }?.tag as? String
    }

    fun setButtonSelected(key: String?, isSelected: Boolean) {
        customSpinnerDialogModelList?.forEachIndexed { index, customSpinnerDialogModel ->
            if (customSpinnerDialogModel?.key == key) {
                setButtonStatePosition(buttons?.get(index), isSelected, index)
            } else {
                setButtonStatePosition(buttons?.get(index), false, index)
            }
        }
    }

    companion object {
        private const val BUTTON_SINGLE_STATE = "BUTTON_SINGLE_STATE"
        private const val BUTTON_LEFT_STATE = "BUTTON_LEFT_STATE"
        private const val BUTTON_RIGHT_STATE = "BUTTON_RIGHT_STATE"
        private const val BUTTON_CENTER_STATE = "BUTTON_CENTER_STATE"
    }
}