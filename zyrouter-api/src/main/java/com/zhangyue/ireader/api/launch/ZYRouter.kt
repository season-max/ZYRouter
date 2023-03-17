package com.zhangyue.ireader.api.launch

import android.app.Application
import android.content.Context
import android.util.Log
import com.zhangyue.ireader.api.core.LogisticsCenter
import com.zhangyue.ireader.api.core.WareHouse
import com.zhangyue.ireader.api.exceptions.IllegalPathException
import com.zhangyue.ireader.api.exceptions.InitException
import com.zhangyue.ireader.api.interfaces.NavigationCallback
import com.zhangyue.ireader.api.module.PostCard
import com.zhangyue.ireader.api.untils.Logger
import java.lang.ref.WeakReference
import java.util.concurrent.atomic.AtomicBoolean

class ZYRouter private constructor() {


    companion object {

        private var context: Application? = null

        private var mHasInit = AtomicBoolean(false)

        @JvmStatic
        fun debuggable(flag: Boolean) {
            Logger.debuggable = flag
        }

        /**
         * 初始化
         * @param context application
         */
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
            return Holder.holder
        }

        fun destroy() {
            mHasInit.set(false)
            WareHouse.clear()
        }
    }

    private object Holder {
        val holder = ZYRouter()
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

    fun navigation(card: PostCard, context: Context?, listener: NavigationCallback?) {
        val meta = WareHouse.routeMap[card.path]
        if (meta == null) {
            listener?.onLost(card.path)
            return
        }
        // 构造 postCard
        card.routeType = meta.routeType
        card.extra = meta.extra
        card.className = meta.className
        card.destination = meta.destination
        card.activityWeakReference = WeakReference(context)


    }

}