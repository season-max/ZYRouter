package com.zhangyue.ireader.annotations

/**
 * 路由路径注解
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class Route(
    val path: String
)
