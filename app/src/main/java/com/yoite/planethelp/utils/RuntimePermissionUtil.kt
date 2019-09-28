package com.yoite.planethelp.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat


fun onRequestsPermissionsResult(grantResults: IntArray,
                                rpResultListener: RPResultListener) {
    if (grantResults.isNotEmpty()) {
        for (grantResult in grantResults) {
            if (grantResult == PackageManager.PERMISSION_GRANTED) {
                rpResultListener.onPermissionGranted()
            } else {
                rpResultListener.onPermissionDenied()
            }
        }
    }
}

fun requestsPermission(activity: Activity, permissions: Array<String>,
                       REQUEST_CODE: Int) {
    ActivityCompat.requestPermissions(activity, permissions, REQUEST_CODE)
}

fun requestsPermission(activity: Activity, permission: String,
                       REQUEST_CODE: Int) {
    ActivityCompat.requestPermissions(activity, arrayOf(permission), REQUEST_CODE)
}

fun checkPermissionGranted(context: Context, permission: String): Boolean {
    return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
}

public interface RPResultListener {
    fun onPermissionGranted()

    fun onPermissionDenied()
}