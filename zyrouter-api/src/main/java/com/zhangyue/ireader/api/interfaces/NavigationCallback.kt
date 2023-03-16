package com.zhangyue.ireader.api.interfaces

interface NavigationCallback {

    /**
     * 无效路径
     * @param path 想要进行路由的路径
     */
    fun onLost(path: String?)

    /**
     * 有效路径，查找到目标类
     * @param path 路径
     * @param destination 目标类
     */
    fun onFound(path: String?, destination: Class<*>?)
}