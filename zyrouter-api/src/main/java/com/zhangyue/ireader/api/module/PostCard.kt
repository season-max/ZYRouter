package com.zhangyue.ireader.api.module

import android.app.Activity
import android.os.Bundle
import com.zhangyue.ireader.api.interfaces.NavigationCallback
import com.zhangyue.ireader.api.launch.ZYRouter
import java.lang.ref.WeakReference

/**
 * 路由核心数据
 */
class PostCard(path: String) : RouteMeta(path) {

    var extra: Bundle? = null

    var reference: WeakReference<Activity>? = null

    /**
     * 路由导航
     */
    fun navigation() {
        navigation(null)
    }

    /**
     * 路由导航
     * @param activity activity
     */
    fun navigation(activity: Activity?) {
        navigation(activity, null)
    }

    /**
     * 路由导航
     * @param activity activity
     * @param listener 回调路由成功或者失败接口
     */
    fun navigation(activity: Activity?, listener: NavigationCallback?) {
        ZYRouter.getInstance().navigation(path, activity, listener)
    }
}