package com.zhangyue.ireader.api.launch

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.util.Log
import com.zhangyue.ireader.api.core.LogisticsCenter
import com.zhangyue.ireader.api.core.WareHouse
import com.zhangyue.ireader.api.exceptions.IllegalPathException
import com.zhangyue.ireader.api.exceptions.InitException
import com.zhangyue.ireader.api.interfaces.NavigationCallback
import com.zhangyue.ireader.api.module.PostCard
import com.zhangyue.ireader.api.untils.Logger
import java.util.concurrent.atomic.AtomicBoolean

class ZYRouter private constructor() {


    companion object {

        private var context: Application? = null

        private var mHasInit = AtomicBoolean(false)

        @JvmStatic
        fun debuggable(flag: Boolean) {
            Logger.debuggable = flag
        }

        @JvmStatic
        fun init(context: Application) {
            if (mHasInit.get()) {
                throw InitException("ZYRouter has already init!!!")
            }
            this.context = context
            // 填充路由表
            LogisticsCenter.init()

            mHasInit.set(true)
            Log.i(Constant.TAG, "init success")
        }

        fun getInstance(): ZYRouter {
            if (!mHasInit.get()) {
                throw InitException("ZYRouter has not init when use")
            }
            return Holder.instance
        }
    }

    private object Holder {
        @SuppressLint("StaticFieldLeak")
        val instance = ZYRouter()
    }

    /**
     *  构建 postCard
     */
    fun build(path: String?): PostCard {
        if (path == null || path.isEmpty()) {
            throw IllegalPathException("非法路由路径！")
        }
        return PostCard(path)
    }

    fun navigation(path: String?, activity: Activity?, listener: NavigationCallback?) {
        val meta = WareHouse.routeMap[path]
    }

}