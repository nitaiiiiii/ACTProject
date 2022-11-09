package com.ci.act.util

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.util.TypedValue
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.androidadvance.topsnackbar.TSnackbar
import com.ci.act.MyApplication
import com.ci.act.R
import com.ci.act.widgets.sneaker.Sneaker
import org.jetbrains.anko.color
import java.text.DecimalFormat
import kotlin.math.abs
import kotlin.math.roundToInt

fun getMinutesFromHours(mHours: String): Long {
    var minutes = 0L
    try {
        val hours = mHours.toDouble()
        if (hours <= 0) {
            return minutes
        }
        val hh = hours.toInt()
        val mm = ((hours - hh) * 100).roundToInt()
        val extra = (mm / 60)
        minutes = (((hh + extra) * 60) + mm % 60).toLong()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return minutes
}

fun getHoursFromMinutes(minutes: Long): Float {
    val d = (minutes / 60)
    val r: Double = (minutes.toDouble() % 60) / 100
    return (d + r).toFloat()
}

fun removeFloatZeroValue(value: Double): String {
    return try {
        val df = DecimalFormat("###.##")
        df.format(value)
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
        "0"
    }

}

fun removeFloatZeroValue(value: String?): String {
    return try {
        val double: Double? = value?.toDouble()
        val df = DecimalFormat("###.##")
        df.format(double)
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
        "0"
    }

}

fun removeFloatZeroValueReturnEmpty(value: String?): String {
    return try {
        val double: Double? = value?.toDouble()
        val df = DecimalFormat("###.##")
        df.format(double)
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
        ""
    }

}

fun getRoundOffValue(value: String): String {
    try {
        val df = DecimalFormat("##,##,##,##,##,##,##0.##")
        return df.format(value.toDouble())
    } catch (e: Exception) {
        return value.toString()
    }
}

fun removeFloatZeroAndNegativeValue(value: String?): String {
    try {
        val double: Double? = value?.toDouble()
        if (double ?: 0.0 <= 0.0) {
            return "0"
        }
        val df = DecimalFormat("###.##")
        return df.format(double)
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
        return "0"
    }

}

private fun changeStatusBarColor(activity: Activity?, color: Int) {
    activity?.let { activity.window?.statusBarColor = color }
}

fun showSnackBar(message: String, context: Activity?, @ColorRes bgColor: Int) {
    var Y1: Float = 0.0f
    var Y2: Float = 0.0f
    val MIN_DISTANCE = 50
    val snackBar =
        context?.findViewById<View>(android.R.id.content)
            ?.let { TSnackbar.make(it, message, TSnackbar.LENGTH_LONG) }
    snackBar?.setActionTextColor(Color.WHITE)
    val snackBarView = snackBar?.view
    snackBarView?.setBackgroundColor(
        ContextCompat.getColor(
            context,
            bgColor
        )
    )
    snackBar?.setMaxWidth(Int.MAX_VALUE)
    val textView =
        snackBarView?.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text) as? TextView
    textView?.setTextColor(Color.WHITE)
    textView?.textAlignment = View.TEXT_ALIGNMENT_CENTER
    textView?.setTextSize(
        TypedValue.COMPLEX_UNIT_PX,
        context.resources?.getDimension(R.dimen.sp13) ?: 12.0f
    )

    val myLayout = snackBar?.view
    myLayout?.setOnTouchListener { p0, event ->
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Y1 = event.y
            }
            MotionEvent.ACTION_UP -> {
                Y2 = event.y
                val deltaX = Y2 - Y1
                if (abs(deltaX) > MIN_DISTANCE) {
                    if (Y2 > Y1) {
                        // top to bottom swipe action
                    } else {
                        // bottom to top swipe action
                        snackBar.dismiss()
                    }
                }
            }


        }
        false
    }
    textView?.gravity = Gravity.CENTER
    textView?.maxLines = 3
    context?.let {
        val mainColorPrimaryDark =
            it.theme?.color(android.R.attr.colorPrimaryDark) ?: ContextCompat.getColor(
                it,
                R.color.colorPrimaryDark
            )
        changeStatusBarColor(it, ContextCompat.getColor(it, bgColor))
        snackBar?.setCallback(object : TSnackbar.Callback() {
            override fun onShown(snackbar: TSnackbar?) {
                super.onShown(snackbar)
                changeStatusBarColor(it, ContextCompat.getColor(it, bgColor))
            }

            override fun onDismissed(snackbar: TSnackbar?, event: Int) {
                super.onDismissed(snackbar, event)
                when (event) {
                    DISMISS_EVENT_CONSECUTIVE -> {
                        return
                    }
                    else -> {
                        changeStatusBarColor(it, mainColorPrimaryDark)
                    }
                }
            }
        })
    }
    snackBar?.show()
}

fun showCurrentStatusBarColor(activity: Activity?) {
    activity?.let {
        val mainColorPrimaryDark =
            it.theme?.color(android.R.attr.colorPrimaryDark) ?: ContextCompat.getColor(
                it,
                R.color.colorPrimaryDark
            )
        changeStatusBarColor(it, mainColorPrimaryDark)
    }

}

fun dialPhone(phoneNumber: String) {
    val call = Uri.parse("tel:${phoneNumber}")
    val dialerIntent = Intent(Intent.ACTION_DIAL, call)
    dialerIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    MyApplication.getApplicationContext().startActivity(dialerIntent)
}

fun openEmailApp(emailId: String) {
    val emailIntent = Intent(
        Intent.ACTION_SENDTO, Uri.fromParts(
            "mailto", emailId, null
        )
    )
    emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailId))
    val chooserIntent = Intent.createChooser(emailIntent, "Send email...")
    chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    MyApplication.getApplicationContext()
        .startActivity(chooserIntent)
}

fun showSnackBar(message: String, activity: Activity?, isSuccess: Boolean) {
    val typeface = Typeface.createFromAsset(activity?.assets, "fonts/HelveticalNeue.ttf")

    if (isSuccess) {
        Sneaker.with(activity ?: Activity())
            .setTitle(message ?: "")
            .setTypeface(typeface)
            .sneakSuccess()
    } else {
        Sneaker.with(activity ?: Activity())
            .setTitle(message ?: "")
            .setTypeface(typeface)
            .sneakError()
    }
}

fun showSnackBarWithWhiteTextCustomColor(message: String, activity: Activity?, color: String?) {
    val typeface = Typeface.createFromAsset(activity?.assets, "fonts/HelveticalNeue.ttf")
    Sneaker.with(activity ?: Activity())
        .setTitle(message ?: "")
        .setTypeface(typeface)
        .sneakWithCustomColorWhiteText(color)
}

fun showSnackBarWithBlackTextCustomColor(message: String, activity: Activity?, color: String?) {
    val typeface = Typeface.createFromAsset(activity?.assets, "fonts/HelveticalNeue.ttf")
    Sneaker.with(activity ?: Activity())
        .setTitle(message ?: "")
        .setTypeface(typeface)
        .sneakWithCustomColorBlackText(color)
}

fun showSnackBar(view: View, message: String, isSuccess: Boolean) {
    val color: Int = if (isSuccess) {
        Color.GREEN
    } else {
        Color.RED
    }
    val snackBar = TSnackbar.make(view, message, TSnackbar.LENGTH_LONG)
        .setAction("Action", null)
    snackBar.setActionTextColor(Color.WHITE)
    val snackBarView = snackBar.view

    val params = view.layoutParams as FrameLayout.LayoutParams
    params.gravity = Gravity.TOP
    params.gravity = Gravity.CENTER_HORIZONTAL
    view.layoutParams = params

    snackBarView.setBackgroundColor(color)
    snackBarView.setPadding(0, 40, 0, 0)
    snackBar.setMaxWidth(Int.MAX_VALUE)
    val textView =
        snackBarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text) as? TextView
    textView?.setTextColor(Color.WHITE)
//    textView?.typeface = ResourcesCompat.getFont(view.context, R.font.pacifico_regular)
    textView?.textAlignment = View.TEXT_ALIGNMENT_CENTER
    textView?.gravity = Gravity.CENTER
    textView?.maxLines = 3
    snackBar.show()
}




