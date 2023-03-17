package com.zhangyue.ireader.api.core

import android.nfc.Tag
import com.zhangyue.ireader.api.exceptions.PathNotFoundException
import com.zhangyue.ireader.api.interfaces.IRouteGroup
import com.zhangyue.ireader.api.launch.Constant
import com.zhangyue.ireader.api.module.PostCard
import com.zhangyue.ireader.api.untils.Logger

/**
 * 物流中心
 * 实现
 * 1.填充路由表
 * 2.实现路由跳转
 */
class LogisticsCenter {

    companion object {

        var fillByPlugin = false

        fun init() {
            fillRouteMap()
        }

        /**
         * 填充路由表
         * 编译期通过插桩方式填充
         */
        private fun fillRouteMap() {
            // register("com.zhangyue.ireader.ZYRoute&&xxxx")
        }

        /**
         * 注册路由
         */
        private fun register(className: String) {
            val cls = Class.forName(className)
            val newInstance = cls.getDeclaredConstructor().newInstance()
            // 类型判断
            if (newInstance is IRouteGroup) {
                fillRouteMapInner(newInstance)
            }
        }

        /**
         * 填充指定的路由
         * @param obj ：根据实例对象进行填充
         */
        private fun fillRouteMapInner(obj: IRouteGroup) {
            Logger.i("填充路由：$obj")
            obj.loadInto(WareHouse.routeMap)
        }

        fun completion(card: PostCard) {
            val meta = WareHouse.routeMap[card.path]
                ?: throw PathNotFoundException("path is not found,path is ${card.path}")
            Logger.i("路由元数据：\n $meta")
            // 构造 postCard
            card.extra = meta.extra
            card.routeType = meta.routeType
            card.className = meta.className
            card.destination = meta.destination
        }

    }
}