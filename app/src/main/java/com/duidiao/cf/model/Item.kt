package com.duidiao.cf.model

/** 列表项数据模型 */
data class Item(
    var index: String = "", //第几局
    var openNorthCheckable: Boolean = false,//zhuangjia
    var openEastCheckable: Boolean = false,//shifoushizhuang
    var openUseBig1Select: Boolean = false,
    var openUseBig2Select: Boolean = false,
    var openUseSmall1Select: Boolean = false,
    var openUseSmall2Select: Boolean = false,
    var openDoubleBigKouSelect: Boolean = false,
    var openDoubleSmallKouSelect: Boolean = false,
    var openSingleBigKouSelect: Boolean = false,
    var openSingleSmallKouSelect: Boolean = false,
    var openScore: Int = 0,
    var openTeam1 : String = "1队",
    var openTeam2 : String = "2队",
    var openWinTeam: String = openTeam1,
    var openResultScore: Int = 0,

    var closeNorthCheckable: Boolean = false,//zhuangjia
    var closeEastCheckable: Boolean = false,//shifoushizhuang
    var closeUseBig1Select: Boolean = false,
    var closeUseBig2Select: Boolean = false,
    var closeUseSmall1Select: Boolean = false,
    var closeUseSmall2Select: Boolean = false,
    var closeDoubleBigKouSelect: Boolean = false,
    var closeDoubleSmallKouSelect: Boolean = false,
    var closeSingleBigKouSelect: Boolean = false,
    var closeSingleSmallKouSelect: Boolean = false,
    var closeScore: Int = 0,

    var closeResultScore : Int = 0,
    var closeTeam1 : String = "1队",
    var closeTeam2 : String = "2队",
    var closeWinTeam: String = closeTeam1,

    var realResultScore : Int = 0,
    var realWinTeam : String = closeWinTeam,
    var isFinished: Boolean =false
    ) {

    fun reset() {
        index = ""
        openNorthCheckable = false//zhuangjia
        openEastCheckable = false//shifoushizhuang
        openUseBig1Select = false
        openUseBig2Select = false
        openUseSmall1Select = false
        openUseSmall2Select = false
        openDoubleBigKouSelect = false
        openDoubleSmallKouSelect = false
        openSingleBigKouSelect = false
        openSingleSmallKouSelect = false
        openScore = 0
        openWinTeam = openTeam1
        openResultScore = 0

        closeNorthCheckable = false//zhuangjia
        closeEastCheckable = false//shifoushizhuang
        closeUseBig1Select = false
        closeUseBig2Select = false
        closeUseSmall1Select = false
        closeUseSmall2Select = false
        closeDoubleBigKouSelect = false
        closeDoubleSmallKouSelect = false
        closeSingleBigKouSelect = false
        closeSingleSmallKouSelect = false
        closeScore = 0

        closeResultScore  = 0
        closeWinTeam = closeTeam1

        realResultScore  = 0
        realWinTeam = closeWinTeam

    }

    override fun toString(): String {
        return "Item(index=$index, openNorthCheckable=$openNorthCheckable, openEastCheckable=$openEastCheckable, " +
                "openUseBig1Select=$openUseBig1Select, openUseBig2Select=$openUseBig2Select, openUseSmall1Select=$openUseSmall1Select, " +
                "openUseSmall2Select=$openUseSmall2Select, openDoubleBigKouSelect=$openDoubleBigKouSelect, " +
                "openDoubleSmallKouSelect=$openDoubleSmallKouSelect, openSingleBigKouSelect=$openSingleBigKouSelect, " +
                "openSingleSmallKouSelect=$openSingleSmallKouSelect, openScore=$openScore, openTeam1='$openTeam1', openTeam2='$openTeam2', openWinTeam='$openWinTeam', " +
                "openResultScore=$openResultScore, closeNorthCheckable=$closeNorthCheckable, closeEastCheckable=$closeEastCheckable, " +
                "closeUseBig1Select=$closeUseBig1Select, closeUseBig2Select=$closeUseBig2Select, closeUseSmall1Select=$closeUseSmall1Select, " +
                "closeUseSmall2Select=$closeUseSmall2Select, closeDoubleBigKouSelect=$closeDoubleBigKouSelect, " +
                "closeDoubleSmallKouSelect=$closeDoubleSmallKouSelect, closeSingleBigKouSelect=$closeSingleBigKouSelect, " +
                "closeSingleSmallKouSelect=$closeSingleSmallKouSelect, closeScore=$closeScore, closeResultScore=$closeResultScore, " +
                "closeTeam1='$closeTeam1', closeTeam2='$closeTeam2', closeWinTeam='$closeWinTeam', realResultScore=$realResultScore, " +
                "realWinTeam='$realWinTeam')"
    }

}