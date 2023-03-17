package com.zhangyue.ireader.api.interfaces

interface IRouteGroup {

    /**
     * 路由存储到路由表
     */
    fun loadInto(map: MutableMap<String, RouteMeta>)

}