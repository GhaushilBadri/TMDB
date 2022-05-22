package com.example.elemestest

import android.app.Activity
import android.app.ProgressDialog

class ExternalMethod {
    companion object{
        fun addLoading(activity: Activity, message: String) : ProgressDialog {
            var progress = ProgressDialog(activity)
            progress.setMessage(message)
            progress.setCancelable(false)
            return progress
        }
    }
}