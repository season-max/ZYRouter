package com.zhangyue.ireader.module

import javax.lang.model.element.Element


/**
 * 路由元数据
 */
open class RouteMeta() {

    /**
     * 路由路径
     */
    var path: String? = null

    var element: Element? = null

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
        this.routeType = builder.routeType
        this.className = builder.className
        this.destination = builder.destination
    }


    override fun toString(): String {
        return "route meta:[" + "\n" +
                "path:${path}" + "\n" +
                "routeType:${routeType}" + "\n" +
                "className:${className}" + "\n" +
                "destination:${destination}" + "\n" +
                "]"
    }

    class Builder {
        @JvmField
        var path: String? = null


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
            type: RouteType,
            className: String,
            destination: Class<*>
        ): RouteMeta {
            return Builder()
                .setPath(path)
                .setRouteType(type)
                .setClassname(className)
                .setDestination(destination)
                .build()
        }
    }


}