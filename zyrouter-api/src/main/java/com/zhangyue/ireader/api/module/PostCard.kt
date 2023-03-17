package com.zhangyue.ireader.api.module

import android.app.Activity
import android.content.Context
import com.zhangyue.ireader.api.interfaces.NavigationCallback
import com.zhangyue.ireader.api.launch.ZYRouter
import java.lang.ref.WeakReference

/**
 * 路由核心数据
 */
class PostCard(path: String) : RouteMeta() {
    init {
        this.path = path
    }

    var activityWeakReference: WeakReference<Context?>? = null


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