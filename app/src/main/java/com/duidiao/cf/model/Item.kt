package com.duidiao.cf.model

/** 列表项数据模型 */
data class Item(
    val index: Int, //第几局
    val openTeam1: Int,// 打成
    val openTeam2: Int,//得分
    val openDashing: Int, //潇洒

    val closeTeam2: Int,//得分
    val closeTeam1: Int, //打成
    val closeDashing: Int, //潇洒

    val winTeam: String,
    val winResult: Int
)