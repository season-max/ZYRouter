package com.zhangyue.ireader.api.module

/**
 * 路由元数据
 */
open class RouteMeta(val path: String) {
    constructor(
        path: String,
        type: RouteType,
        className: String,
        destination: Class<*>
    ) : this(path) {
        this.type = type
        this.className = className
        this.destination = destination
    }

    /**
     * 目标类的规范名称
     */
    var className: String? = null

    /**
     * 目标类
     */
    var destination: Class<*>? = null

    /**
     * 跳转类型
     */
    var type: RouteType? = null

    override fun toString(): String {
        return "RouteMeta(path='$path', className=$className, destination=$destination, type=$type)"
    }

    companion object {
        /**
         * 用于 APT 构建对象
         */
        @JvmStatic
        fun build(
            path: String,
            type: RouteType,
            className: String,
            destination: Class<*>
        ): RouteMeta {
            return RouteMeta(path, type, className, destination)
        }
    }


}