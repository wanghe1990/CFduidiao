package com.duidiao.cf.model

/** 列表项数据模型 */
data class Item(
    val index: Int = 1, //第几局
    val openNorthCheckable: Boolean = false,//zhuangjia
    val openEastCheckable: Boolean = false,//shifoushizhuang
    val openUseBig1Select: Boolean = false,
    val openUseBig2Select: Boolean = false,
    val openUseSmall1Select: Boolean = false,
    val openUseSmall2Select: Boolean = false,
    val openDoubleBigKouSelect: Boolean = false,
    val openDoubleSmallKouSelect: Boolean = false,
    val openSingleBigKouSelect: Boolean = false,
    val openSingleSmallKouSelect: Boolean = false,
    val openScore: Int = 0,
    var openTeam: Int = 1,

    val closeNorthCheckable: Boolean = false,//zhuangjia
    val closeEastCheckable: Boolean = false,//shifoushizhuang
    val closeUseBig1Select: Boolean = false,
    val closeUseBig2Select: Boolean = false,
    val closeUseSmall1Select: Boolean = false,
    val closeUseSmall2Select: Boolean = false,
    val closeDoubleBigKouSelect: Boolean = false,
    val closeDoubleSmallKouSelect: Boolean = false,
    val closeSingleBigKouSelect: Boolean = false,
    val closeSingleSmallKouSelect: Boolean = false,
    val closeScore: Int = 0,

    val closeTeam: Int = 1

) {
    override fun toString(): String {
        return "Item(index=$index, openNorthCheckable=$openNorthCheckable, openEastCheckable=$openEastCheckable, " +
                "openUseBig1Select=$openUseBig1Select, openUseBig2Select=$openUseBig2Select, openUseSmall1Select=$openUseSmall1Select, " +
                "openUseSmall2Select=$openUseSmall2Select, openDoubleBigKouSelect=$openDoubleBigKouSelect, " +
                "openDoubleSmallKouSelect=$openDoubleSmallKouSelect, openSingleBigKouSelect=$openSingleBigKouSelect, " +
                "openSingleSmallKouSelect=$openSingleSmallKouSelect, openScore=$openScore, closeNorthCheckable=$closeNorthCheckable," +
                " closeEastCheckable=$closeEastCheckable, closeUseBig1Select=$closeUseBig1Select, closeUseBig2Select=$closeUseBig2Select, " +
                "closeUseSmall1Select=$closeUseSmall1Select, closeUseSmall2Select=$closeUseSmall2Select, " +
                "closeDoubleBigKouSelect=$closeDoubleBigKouSelect, closeDoubleSmallKouSelect=$closeDoubleSmallKouSelect, " +
                "closeSingleBigKouSelect=$closeSingleBigKouSelect, closeSingleSmallKouSelect=$closeSingleSmallKouSelect, closeScore=$closeScore)"
    }
}