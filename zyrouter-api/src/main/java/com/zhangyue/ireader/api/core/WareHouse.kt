package com.zhangyue.ireader.api.core

import com.zhangyue.ireader.api.module.RouteMeta

/**
 * 路由表
 * 存储路由映射
 */
class WareHouse {

    companion object {
        var routeMap: MutableMap<String, RouteMeta> = HashMap()

        fun clear() {
            routeMap.clear()
        }
    }
}