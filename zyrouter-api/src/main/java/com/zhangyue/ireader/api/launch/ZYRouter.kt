package com.zhangyue.ireader.api.launch

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import com.zhangyue.ireader.api.core.LogisticsCenter
import com.zhangyue.ireader.api.core.WareHouse
import com.zhangyue.ireader.api.exceptions.IllegalPathException
import com.zhangyue.ireader.api.exceptions.IllegalTypeException
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

        private var mHandler: Handler? = null

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
            this.mHandler = Handler(Looper.getMainLooper())
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
        card.activityWeakReference = WeakReference(context ?: ZYRouter.context)
        try {
            LogisticsCenter.completion(card)
        } catch (e: Exception) {
            listener?.onLost(card.path)
        }

        Logger.i("构建明信片数据：\n $card")

        listener?.onFound(card.path, card.destination)

        navigationReal(card)

    }

    private fun navigationReal(card: PostCard): Any? {
        val context = card.activityWeakReference?.get()
            ?: throw RuntimeException("ZYRouter context is null!!!")
        when (card.routeType) {
            // activity
            RouteType.ACTIVITY -> {
                val intent = Intent(context, card.destination)
                card.bundle?.let { intent.putExtras(it) }
                intent.flags = card.flag
                // context 不是 activity，添加 FLAG_ACTIVITY_NEW_TASK flag
                if (context !is Activity) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                intent.action = card.action
                runOnUI {
                    context.startActivity(intent)
                }
            }
            // fragment
            RouteType.FRAGMENT, RouteType.BROADCAST -> {
                val fragment = card.destination?.getDeclaredConstructor()?.newInstance()
                when (fragment) {
                    is Fragment -> {
                        card.bundle?.let {
                            fragment.arguments = it
                        }
                    }
                }
                return fragment
            }
            // error
            else -> {
                throw IllegalTypeException("illegal navigation type,type is ${card.routeType}")
            }
        }
        return null
    }


    private fun runOnUI(run: () -> Unit) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            run()
        } else {
            mHandler?.post(run)
        }
    }

}