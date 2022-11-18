package com.ci.act.ui.authentication.accessLocation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.CompoundButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.ci.act.BR
import com.ci.act.R
import com.ci.act.base.BaseActivity
import com.ci.act.databinding.ActivityAccessLocationBinding
import com.ci.act.prefrence.PreferenceHelper
import com.ci.act.ui.authentication.pushNotification.PushNotificationActivity
import com.ci.act.ui.differentSports.DifferentSportsActivity
import com.ci.act.ui.onboarding.OnBoardingActivity
import com.ci.act.util.showSnackBar
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.DexterError
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.util.*

class AccessLocationActivity :
    BaseActivity<ActivityAccessLocationBinding, AccessLocationView, AccessLocationViewModel>(),
    AccessLocationView {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun getContentView(): Int = R.layout.activity_access_location

    override fun setViewModelClass(): Class<AccessLocationViewModel> {
        return AccessLocationViewModel::class.java
    }

    override fun getNavigator(): AccessLocationView = this

    override fun getBindingVariable(): Int = BR.accessLocation

    override fun initViews(savedInstanceState: Bundle?) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)


        setOnClickListeners()

    }

    private fun checkPermissionsLocation() {
        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(multiplePermissionsReport: MultiplePermissionsReport) {
                    if (multiplePermissionsReport.areAllPermissionsGranted()) {
                        fetchLocation()
                    }
                    if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied) {
                        showSettingsDialog()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    list: List<PermissionRequest>,
                    permissionToken: PermissionToken
                ) {
                    permissionToken.continuePermissionRequest()
                }
            }).withErrorListener { error: DexterError? ->
                Toast.makeText(this,"this is toast message",Toast.LENGTH_SHORT).show()

            }.onSameThread().check()
    }

    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val task = fusedLocationProviderClient.lastLocation
        task.addOnSuccessListener { location ->
            if (location != null) {
                val geocoder = Geocoder(this, Locale.getDefault())
                val addresses = geocoder.getFromLocation(
                    location.latitude,
                    location.longitude,
                    1
                )

                val state: String = addresses?.get(0)?.adminArea!!
                val city: String = addresses[0].locality

                mViewDataBinding?.editTextLocationFirstName?.setText(state)
                mViewDataBinding?.editTextLocationLastName?.setText(city)
            }
        }
    }


    private fun showSettingsDialog() {
        val builder = MaterialAlertDialogBuilder(this, R.style.MaterialAlertDialog_rounded)
        builder.setTitle("Need Permissions")

        builder.setMessage("This app needs location permission to use this feature. You can grant them in app settings.")
        builder.setPositiveButton("GOTO SETTINGS") { dialog, which ->
            dialog.cancel()
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri: Uri = Uri.fromParts("package", this.packageName, null)
            intent.data = uri
            startActivity(intent)
        }
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }
        builder.create().show()
    }

    private fun setOnClickListeners() {

        mViewDataBinding?.imgBackButton?.setOnClickListener {
            val intent = Intent(this, DifferentSportsActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
        mViewDataBinding?.txtSkipAccess?.setOnClickListener {
            PreferenceHelper.getInstance().finishedChooseSports()
            val intent = Intent(this, OnBoardingActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
        mViewDataBinding?.btnAccessLocation?.setOnClickListener {
            PreferenceHelper.getInstance().finishedChooseSports()
            val intent = Intent(this, PushNotificationActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        var switchState: Boolean = false
        mViewDataBinding?.mainSwitching?.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { p0, p1 ->
            if (p1) {
                checkPermissionsLocation()
                mViewDataBinding?.editTextLocationFirstName?.isEnabled = false
                mViewDataBinding?.editTextLocationLastName?.isEnabled = false
            } else {
                mViewDataBinding?.editTextLocationFirstName?.isEnabled = true
                mViewDataBinding?.editTextLocationLastName?.isEnabled = true
                mViewDataBinding?.editTextLocationFirstName?.text?.clear()
                mViewDataBinding?.editTextLocationLastName?.text?.clear()
            }
            switchState = p1
        })

        mViewDataBinding?.btnAccessLocation?.setOnClickListener {
            if (switchState) {
                val intent = Intent(this,OnBoardingActivity::class.java)
                startActivity(intent)
            } else {
                if (mViewDataBinding?.editTextLocationFirstName?.text?.isEmpty()!!) {
                    mViewDataBinding?.editTextLocationLastName?.requestFocus()
                    Toast.makeText(this,"this is toast message",Toast.LENGTH_SHORT).show()
                } else if (mViewDataBinding?.editTextLocationFirstName?.text?.isEmpty()!!) {
                    mViewDataBinding?.editTextLocationFirstName?.requestFocus()
                    Toast.makeText(this,"this is toast message",Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(this,OnBoardingActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

    }


}