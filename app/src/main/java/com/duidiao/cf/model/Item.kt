package com.duidiao.cf.model

/** 列表项数据模型 */
data class Item(
    val index: Int,        // 第几局
    val team1: String,   // 项目名称
    val team2: String? = null  // 可选描述（示例扩展字段）




)