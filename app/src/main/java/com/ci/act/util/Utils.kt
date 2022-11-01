package com.ci.act.util

import java.text.SimpleDateFormat
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Base64
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.text.HtmlCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import java.io.*
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.regex.Pattern


class Utils {

    companion object {

        val requestsDateFormat = SimpleDateFormat("yyyy/MM/dd")
        val responseDateFormat = SimpleDateFormat("yyyy-MM-dd")


        /**
         * Hide the keyboard
         */
        fun hideKeyboard(activity: Activity) {
            val view = activity.findViewById<View>(android.R.id.content)
            if (view != null) {
                val imm =
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        /**
         * Move cursor to last position
         */
        fun moveCursorToLast(editText: EditText) {
            editText.setSelection(editText.text.toString().length)
        }

        /**
         * Convert string to MD5 format
         */
        fun getMD5Password(input: String): String {
            val digest: MessageDigest
            var mD5Password: String
            mD5Password = ""
            try {
                digest = MessageDigest.getInstance("MD5")
                digest.update(input.toByteArray(), 0, input.length)
                mD5Password = BigInteger(1, digest.digest()).toString(16)
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }
            return mD5Password
        }

        /**
         * Convert internal file to Base64
         */
        fun encodeFileToBase64(filePath: String): String {
            val yourFile = File(filePath)
            val size = yourFile.length().toInt()
            val bytes = ByteArray(size)
            try {
                val buf = BufferedInputStream(FileInputStream(yourFile))
                buf.read(bytes, 0, bytes.size)
                buf.close()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return Base64.encodeToString(bytes, Base64.NO_WRAP)
        }

        /**
         * Convert Image file to base64 with compress
         */
        fun encodeImageFileToBase64(path: String?): String? {
            val bmp: Bitmap?
            val baos: ByteArrayOutputStream?
            val baat: ByteArray?
            var encodeString: String? = null
            try {
                bmp = BitmapFactory.decodeFile(path)
                baos = ByteArrayOutputStream()
                bmp!!.compress(Bitmap.CompressFormat.JPEG, 50, baos)
                Log.e("BitmapSize", bmp.byteCount.toString())
                baat = baos.toByteArray()
                encodeString = Base64.encodeToString(baat, Base64.NO_WRAP)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return encodeString
        }

        /**
         * Image Loading Place holder  for Glide
         */
        fun getImagePlaceHolderLoading(context: Context): CircularProgressDrawable {
            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            return circularProgressDrawable
        }

        fun isEmailValid(email: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches()
        }

        fun isPassValid(pass: String): Boolean {
            return PASSWORD.matcher(pass).matches()
        }

        val PASSWORD = Pattern.compile(
            "(?=.*?[a-zA-Z])" +
                    "(?=.*?[0-9])" +
                    ".{8,}"
        )

        fun shareData(context: Context?, shareUrl: String?) {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "App link")
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareUrl)
            context?.startActivity(Intent.createChooser(sharingIntent, "Share App Link Via :"))
        }

        fun isNetworkAvailable(activity: Activity?) =
            (activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
                getNetworkCapabilities(activeNetwork)?.run {
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                            || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                            || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                } ?: false
            }


        fun checkLocationFetchAccess(context: Context?): Boolean {
            val lm = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            var gpsEnabled = false
            var networkEnabled = false

            try {
                gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
            } catch (ex: Exception) {

            }

            try {
                networkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

            return !(!gpsEnabled && !networkEnabled)
        }

        fun getTextAsterisk(): String {
            return HtmlCompat.fromHtml(" <font >*</font>", HtmlCompat.FROM_HTML_MODE_COMPACT)
                .toString()
        }

        fun checkGPSStatus(context: Context?): Boolean {
            val lm = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            var gpsEnabled = false

            try {
                gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
            } catch (ex: Exception) {

            }
            return gpsEnabled
        }
    }

}