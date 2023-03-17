package com.zhangyue.ireader.api.module

import android.os.Bundle

/**
 * 路由元数据
 */
open class RouteMeta() {

    /**
     * 路由路径
     */
    var path: String? = null

    /**
     * 携带的 bundle 数据
     */
    var extra: Bundle? = null

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
    var routeType: RouteType? = null

    constructor(builder: Builder) : this() {
        this.path = builder.path
        this.extra = builder.extra
        this.routeType = builder.routeType
        this.className = builder.className
        this.destination = builder.destination
    }


    override fun toString(): String {
        return "RouteMeta(path='$path', className=$className, destination=$destination, type=$routeType)"
    }

    class Builder {
        @JvmField
        var path: String? = null

        @JvmField
        var extra: Bundle? = null

        @JvmField
        var className: String? = null

        @JvmField
        var destination: Class<*>? = null

        @JvmField
        var routeType: RouteType? = null

        fun setPath(path: String): Builder {
            this.path = path
            return this
        }

        fun setExtra(extra: Bundle): Builder {
            this.extra = extra
            return this
        }

        fun setClassname(className: String): Builder {
            this.className = className
            return this
        }

        fun setDestination(cls: Class<*>): Builder {
            this.destination = cls
            return this
        }

        fun setRouteType(type: RouteType): Builder {
            this.routeType = type
            return this
        }

        fun build(): RouteMeta {
            return RouteMeta(this)
        }

    }

    companion object {
        /**
         * 用于 APT 构建对象
         */
        @JvmStatic
        fun build(
            path: String,
            extra: Bundle,
            type: RouteType,
            className: String,
            destination: Class<*>
        ): RouteMeta {
            return Builder()
                .setPath(path)
                .setExtra(extra)
                .setRouteType(type)
                .setClassname(className)
                .setDestination(destination)
                .build()
        }
    }


}