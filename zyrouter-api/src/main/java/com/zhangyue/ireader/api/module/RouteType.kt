package com.zhangyue.ireader.api.module

/**
 * 路由跳转类型
 * Activity
 * fragment
 * broadcast
 */
enum class RouteType(val type: Int) {
    ACTIVITY(0),
    FRAGMENT(1),
    BROADCAST(2)
}