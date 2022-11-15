package com.ci.act.ui.home.settingsPage.fragments.locationSecondFragment

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
import com.ci.act.base.BaseFragment
import com.ci.act.databinding.FragmentLocationFirstBinding
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

class LocationFirstFragment :
    BaseFragment<FragmentLocationFirstBinding, LocationFirstView, LocationFirstViewModel>(),
    LocationFirstView {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun getContentView(): Int = R.layout.fragment_location_first

    override fun setViewModel(): LocationFirstViewModel? = LocationFirstViewModel()

    override fun getBindingVariable(): Int = BR.locationFirstFragment

    override fun getNavigator(): LocationFirstView = this

    override fun initViews(savedInstanceState: Bundle?) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        setOnClickListeners()
    }

    private fun checkPermissionsLocation() {
        Dexter.withContext(requireContext())
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
                Toast.makeText(requireContext(),"this is toast message", Toast.LENGTH_SHORT).show()
            }.onSameThread().check()
    }

    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val task = fusedLocationProviderClient.lastLocation
        task.addOnSuccessListener { location ->
            if (location != null) {
                val geocoder = Geocoder(requireContext(), Locale.getDefault())
                val addresses = geocoder.getFromLocation(
                    location.latitude,
                    location.longitude,
                    1
                )

                val state: String = addresses?.get(0)?.adminArea!!
                val city: String = addresses[0].locality

                mViewDataBinding?.editTextLocationFirstName?.setText(state)
                mViewDataBinding?.editTextLocationEndName?.setText(city)
            }
        }
    }

    /**
     * this method is called when permissions are permanently denied and need to be granted using settings.
     */
    private fun showSettingsDialog() {
        val builder = MaterialAlertDialogBuilder(requireActivity(), R.style.MaterialAlertDialog_rounded)
        builder.setTitle("Need Permissions")

        builder.setMessage("This app needs location permission to use this feature. You can grant them in app settings.")
        builder.setPositiveButton("GOTO SETTINGS") { dialog, which ->
            dialog.cancel()
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri: Uri = Uri.fromParts("package", requireContext().packageName, null)
            intent.data = uri
            startActivity(intent)
        }
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }
        builder.create().show()
    }

    private fun setOnClickListeners() {
        var switchState: Boolean = false
        mViewDataBinding?.mainLocationSwitch?.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { p0, p1 ->
            if (p1) {
                checkPermissionsLocation()
                mViewDataBinding?.editTextLocationFirstName?.isEnabled = false
                mViewDataBinding?.editTextLocationEndName?.isEnabled = false
            } else {
                mViewDataBinding?.editTextLocationFirstName?.isEnabled = true
                mViewDataBinding?.editTextLocationEndName?.isEnabled = true
                mViewDataBinding?.editTextLocationFirstName?.text?.clear()
                mViewDataBinding?.editTextLocationEndName?.text?.clear()
            }
            switchState = p1
        })
    }

    override fun addObservables() {
    }

}