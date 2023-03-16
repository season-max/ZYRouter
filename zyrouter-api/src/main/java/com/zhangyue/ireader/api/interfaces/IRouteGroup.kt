package com.zhangyue.ireader.api.interfaces

import com.zhangyue.ireader.api.module.RouteMeta

interface IRouteGroup {

    fun loadInto(map: MutableMap<String, RouteMeta>)

}