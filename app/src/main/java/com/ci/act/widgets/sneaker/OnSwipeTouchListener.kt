package com.ci.act.widgets.sneaker

import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import kotlin.math.abs

/**
 * Created by TharunReddy 17/09/2021
 */
abstract class OnSwipeTouchListener : OnTouchListener {
    private val gestureDetector: GestureDetector

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    private inner class GestureListener : SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            var result = false
            try {
                val diffY = e2.y - e1.y
                val diffX = e2.x - e1.x
                printLogcat("diffX-> $diffX")
                printLogcat("diffY-> $diffY")
                if (abs(diffX) > abs(diffY)) {
                    if (abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight()
                        } else {
                            onSwipeLeft()
                        }
                        result = true
                    }
                } else if (abs(diffY) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    printLogcat("y initiative")
                    if (diffY > 0) {
                        onSwipeBottom()
                        printLogcat("y bottom")
                    } else {
                        printLogcat("y top")
                        onSwipeTop()
                    }
                    result = true
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
            return result
        }


    }
    companion object {
        private const val SWIPE_THRESHOLD = 50
        private const val SWIPE_VELOCITY_THRESHOLD = 50
    }
    abstract fun onSwipeRight()
    abstract fun onSwipeLeft()
    abstract fun onSwipeTop()
    abstract fun onSwipeBottom()

    init {
        gestureDetector = GestureDetector(GestureListener())
    }
    private fun printLogcat(message:String){
        Log.e("OnSwipeTouchListener",message)
    }
}