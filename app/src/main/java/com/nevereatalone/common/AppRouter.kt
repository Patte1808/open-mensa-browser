package com.nevereatalone.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log

class AppRouter(val context: Context) {

    fun current(){
        Log.i("Router","...")
    }
    fun goTo(activityClass: Class<out Activity>): Intent {
        return Intent(context, activityClass)
    }
}