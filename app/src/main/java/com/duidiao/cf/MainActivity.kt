package com.duidiao.cf

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.duidiao.cf.adapter.MyAdapter
import com.duidiao.cf.model.Item

class MainActivity : ComponentActivity() {

    private val TAG = "MainActivity"
    private var recyclerView: RecyclerView? = null
    private var dataList: List<Item>? = null
    private var start: TextView? = null
    private var result: TextView? = null
    private var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initView()
        initList()
        initRecycleView()
    }

    private fun initList() {
        var item = Item()
        dataList = listOf(item, item, item)
    }

    private fun initRecycleView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView?.layoutManager = layoutManager

        // 设置PagerSnapHelper实现整页滑动
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        // 禁用过度滚动效果
        recyclerView?.overScrollMode = View.OVER_SCROLL_NEVER


        val adapter = MyAdapter(dataList)
        recyclerView?.adapter = adapter

        // 添加页面切换监听
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    currentPosition = layoutManager.findFirstCompletelyVisibleItemPosition()
                    if (currentPosition != RecyclerView.NO_POSITION) {
                        Log.i(TAG, "currentPosition,     position$currentPosition")
                    }
                }
            }
        })
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)
        start = findViewById(R.id.tv_start)
        result = findViewById(R.id.tv_result)

        start?.setOnClickListener {
            handleScore()
        }
    }

    private fun handleScore() {
        var date = dataList?.get(currentPosition) ?: Item()
        Log.i(TAG, "date = ${date.toString()}")

        date.openResultScore = 0
        date.openWinTeam = if (date.openEastCheckable) date.openTeam1 else date.openTeam2
        //开室计算
        if (date.openUseBig1Select) {
            date.openResultScore += 3
        }
        if (date.openUseBig2Select) {
            date.openResultScore += 3
        }
        if (date.openUseSmall1Select) {
            date.openResultScore += 2
        }
        if (date.openUseSmall2Select) {
            date.openResultScore += 2
        }
        //扣抵
        if (date.openDoubleBigKouSelect) {
            date.openWinTeam = if (date.openEastCheckable) date.openTeam2 else date.openTeam1
            date.openResultScore += 6
        }
        if (date.openDoubleSmallKouSelect) {
            date.openWinTeam = if (date.openEastCheckable) date.openTeam2 else date.openTeam1
            date.openResultScore += 4
        }
        if (date.openSingleBigKouSelect) {
            date.openWinTeam = if (date.openEastCheckable) date.openTeam2 else date.openTeam1
            date.openResultScore += 3
        }
        if (date.openSingleSmallKouSelect) {
            date.openWinTeam = if (date.openEastCheckable) date.openTeam2 else date.openTeam1
            date.openResultScore += 2
        }

        if (date.openScore >= 80) {
            date.openWinTeam = if (date.openEastCheckable) date.openTeam2 else date.openTeam1
        } else {
            date.openWinTeam = if (date.openEastCheckable) date.openTeam1 else date.openTeam2
        }

        //闭室计算
        date.closeResultScore = 0
        date.closeWinTeam = if (date.closeEastCheckable) date.closeTeam2 else date.closeTeam1
        //潇洒
        if (date.closeUseBig1Select) {
            date.closeResultScore += 3
        }
        if (date.closeUseBig2Select) {
            date.closeResultScore += 3
        }
        if (date.closeUseSmall1Select) {
            date.closeResultScore += 2
        }
        if (date.closeUseSmall2Select) {
            date.closeResultScore += 2
        }
        //扣抵
        if (date.closeDoubleBigKouSelect) {
            date.closeWinTeam = if (date.closeEastCheckable) date.closeTeam1 else date.closeTeam2
            date.closeResultScore += 6
        }
        if (date.closeDoubleSmallKouSelect) {
            date.closeWinTeam = if (date.closeEastCheckable) date.closeTeam1 else date.closeTeam2
            date.closeResultScore += 4
        }
        if (date.closeSingleBigKouSelect) {
            date.closeWinTeam = if (date.closeEastCheckable) date.closeTeam1 else date.closeTeam2
            date.closeResultScore += 3
        }
        if (date.closeSingleSmallKouSelect) {
            date.closeWinTeam = if (date.closeEastCheckable) date.closeTeam1 else date.closeTeam2
            date.closeResultScore += 2
        }

        if (date.closeScore >= 80) {
            date.closeWinTeam = if (date.closeEastCheckable) date.closeTeam1 else date.closeTeam2
        } else {
            date.closeWinTeam = if (date.closeEastCheckable) date.closeTeam2 else date.closeTeam1
        }



        if (date.openWinTeam == date.closeWinTeam) {
            date.realResultScore = date.openResultScore + date.closeResultScore
            date.realWinTeam = date.openWinTeam
        } else {
            if (date.openResultScore > date.closeResultScore) {
                date.realResultScore = date.openResultScore - date.closeResultScore
                date.realWinTeam = date.openWinTeam
            } else if (date.openResultScore < date.closeResultScore){
                date.realResultScore = date.closeResultScore - date.openResultScore
                date.realWinTeam = date.closeWinTeam
            } else {
                if (date.openScore > date.closeScore) {
                    date.realWinTeam = date.closeWinTeam
                    date.realResultScore = 1
                } else if (date.openScore < date.closeScore){
                    date.realWinTeam = date.openWinTeam
                    date.realResultScore = 1
                }
            }
        }


        result?.text = "赢家：" + date.realWinTeam + ",   得分： " + date.realResultScore

    }

}