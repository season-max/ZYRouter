package com.zhangyue.ireader.api.core

import com.zhangyue.ireader.api.interfaces.IRouteGroup
import com.zhangyue.ireader.api.launch.Constant
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
            val interfaces = cls.interfaces
            if (IRouteGroup::class.java in interfaces) {
                Logger.i("查找到需要注册的路由 $cls")
                fillRouteMapInner(cls)
                fillByPlugin = true
            }
        }

        /**
         * 填充指定的路由
         * @param cls ：根据 class 对象进行填充
         */
        private fun fillRouteMapInner(cls: Class<*>) {
            // 创建实例
            val instance = cls.getDeclaredConstructor().newInstance()
            // 调用填充方法
            val method = cls.getDeclaredMethod(Constant.METHOD_NAME_LOAD_INFO)
            method.isAccessible = true
            method.invoke(instance, WareHouse.routeMap)
        }


    }
}