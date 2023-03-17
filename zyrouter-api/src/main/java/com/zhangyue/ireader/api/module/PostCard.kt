package com.zhangyue.ireader.api.module

import android.content.Context
import android.os.Bundle
import com.zhangyue.ireader.api.interfaces.NavigationCallback
import com.zhangyue.ireader.api.launch.ZYRouter
import com.zhangyue.ireader.module.RouteMeta
import java.lang.ref.WeakReference

/**
 * 路由核心数据
 */
class PostCard(path: String) : RouteMeta() {
    init {
        this.path = path
    }

    var activityWeakReference: WeakReference<Context?>? = null

    @JvmField
    var bundle: Bundle? = null

    @JvmField
    var action: String? = null

    @JvmField
    var flag: Int = 0


    fun withBundle(bundle: Bundle) {
        this.bundle = bundle
    }

    fun withAction(action: String) {
        this.action = action
    }

    fun withFlag(flag: Int) {
        this.flag = flag
    }

    override fun toString(): String {
        return "postCard:[" + "\n" +
                "path:${path}" + "\n" +
                "routeType:${routeType}" + "\n" +
                "className:${className}" + "\n" +
                "destination:${destination}" + "\n" +
                "bundle:${bundle}" + "\n" +
                "action:${action}" + "\n" +
                "flag:${flag}" + "\n" +
                "]"
    }


    /**
     * 路由导航
     * @param context 应用上下文
     * @param listener 回调路由成功或者失败接口
     */
    fun navigation(
        context: Context? = null,
        listener: NavigationCallback? = null
    ) {
        ZYRouter.getInstance().navigation(this, context, listener)
    }
}