package com.ci.act.ui.home.settingsPage.fragments.notificationsFirstFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.EditText
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseFragment
import com.ci.act.databinding.FragmentNotificationFirstBinding
import com.ci.act.widgets.CustomEditText
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*

class NotificationFirstFragment :
    BaseFragment<FragmentNotificationFirstBinding, NotificationFirstView, NotificationFirstViewModel>(),
    NotificationFirstView {

    private lateinit var picker: MaterialTimePicker
    private var selectedTime: String? = null
    private lateinit var calendar: Calendar

    override fun getContentView(): Int = R.layout.fragment_notification_first

    override fun setViewModel(): NotificationFirstViewModel? = NotificationFirstViewModel()


    override fun getBindingVariable(): Int = BR.notificationFirstFragment

    override fun getNavigator(): NotificationFirstView = this

    override fun initViews(savedInstanceState: Bundle?) {

       setOnClickListeners()
    }

    private fun setOnClickListeners() {
        var switchState: Boolean = false
        mViewDataBinding?.mainSwitch?.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { p0, p1 ->
            if (p1) {
                mViewDataBinding?.txtLocationFirstAccess?.text = "Enabled"
            } else {
                mViewDataBinding?.txtLocationFirstAccess?.text = "Disabled"
            }
            switchState = p1
        })

        mViewDataBinding?.imgLocationFirst?.setOnClickListener {
            mViewDataBinding?.imgLocationFirst?.visibility = View.INVISIBLE
//          mViewDataBinding?.imgUncheck?.visibility = View.VISIBLE
            mViewDataBinding?.imgLocationFirst?.visibility = View.VISIBLE

            setTime(false)
        }

        mViewDataBinding?.imgLocationFirst1?.setOnClickListener {
            mViewDataBinding?.imgLocationFirst1?.visibility = View.VISIBLE
            mViewDataBinding?.imgLocationFirst1?.visibility = View.INVISIBLE
            setTime(true)
        }

        mViewDataBinding?.editTextLocationEmailAddress?.setOnClickListener {
            showTimePicker(mViewDataBinding?.editTextLocationEmailAddress!!)
        }

        mViewDataBinding?.editTextLocation2?.setOnClickListener {
            showTimePicker(mViewDataBinding?.editTextLocation2!!)
        }
    }

    private fun setTime(showTime: Boolean) {
        if (showTime) {
            getCurrentTime()
            mViewDataBinding?.editTextLocationEmailAddress?.setText(selectedTime.toString())
            mViewDataBinding?.editTextLocation2?.setText(selectedTime.toString())
        } else {
            mViewDataBinding?.editTextLocationEmailAddress?.text?.clear()
            mViewDataBinding?.editTextLocation2?.text?.clear()
        }
    }

    /**
     * to get current time.
     */
    private fun getCurrentTime() {
        val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
        selectedTime = sdf.format(Date()).lowercase()
    }

    /**
     * open a time picker dialog
     */
    @SuppressLint("SetTextI18n")
    private fun showTimePicker(editText: CustomEditText) {
        picker = MaterialTimePicker.Builder()
            .setTheme(R.style.ThemeOverlay_App_MaterialCalendar)
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Notification Time")
//            .setInputMode(MaterialTimePicker.INPUT_MODE_TEXT)
            .build()

        picker.show(requireActivity().supportFragmentManager, "Time Picker")

        picker.addOnPositiveButtonClickListener {
            if (picker.hour > 12) {
                selectedTime =
                    String.format("%02d", picker.hour - 12) + ":" + String.format(
                        "%02d",
                        picker.minute
                    ) + " pm"
                editText.setText(selectedTime)
                Log.e("time pick", "$selectedTime")
            } else {
                selectedTime =
                    String.format("%02d", picker.hour) + ":" + String.format(
                        "%02d",
                        picker.minute
                    ) + " am"
                editText.setText(selectedTime)
                Log.e("time pick", "$selectedTime")
            }

            calendar = Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY] = picker.hour
            calendar[Calendar.MINUTE] = picker.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0
        }
    }


}