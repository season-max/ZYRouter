package com.zhangyue.ireader.api.untils

import android.util.Log
import com.zhangyue.ireader.api.launch.Constant

object Logger {

    var debuggable = false

    fun d(msg: String) {
        if (debuggable) {
            Log.d(Constant.TAG, msg)
        }
    }

    fun i(msg: String) {
        if (debuggable) {
            Log.i(Constant.TAG, msg)
        }
    }

    fun w(msg: String) {
        if (debuggable) {
            Log.w(Constant.TAG, msg)
        }
    }

    fun e(msg: String) {
        if (debuggable) {
            Log.e(Constant.TAG, msg)
        }
    }

}