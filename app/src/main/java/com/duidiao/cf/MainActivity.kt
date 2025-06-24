package com.duidiao.cf

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.duidiao.cf.adapter.MyAdapter
import com.duidiao.cf.model.Item

class MainActivity : ComponentActivity() {

    private val TAG = "MainActivity"
    private var recyclerView: RecyclerView? = null
    private val dateList = mutableListOf<Item>()
    private var start: TextView? = null
    private var result: TextView? = null
    private var index: TextView? = null
    private var next: ImageView? = null
    private var delete: ImageView? = null
    private var currentPosition = 0
    private var adapter: MyAdapter? = null
    private var localTeam1:String = ""
    private var localTeam2:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initView()
        initList()
        initRecycleView()
        showDialog()
    }

    private fun showDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog_view, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)
        val dialog = builder.create()

        val team1 = dialogView.findViewById<EditText>(R.id.team1)
        val team2 = dialogView.findViewById<EditText>(R.id.team2)
        val save = dialogView.findViewById<TextView>(R.id.save)

        save.setOnClickListener {
            val team1 = team1.text.toString()
            val team2 = team2.text.toString()
            if (team1.isEmpty()) {
                Toast.makeText(this, "战队1 名称不能为空", Toast.LENGTH_SHORT).show()
            }
            if (team2.isEmpty()) {
                Toast.makeText(this, "战队2 名称不能为空", Toast.LENGTH_SHORT).show()
            }
            if (team1.length > 6 || team2.length > 6){
                Toast.makeText(this, "战队名称限定6个字以内", Toast.LENGTH_SHORT).show()
            }

            localTeam1 = team1
            localTeam2 = team2
            dateList[0].openTeam1 = localTeam1
            dateList[0].openTeam2= localTeam2
            dateList[0].closeTeam1 = localTeam1
            dateList[0].closeTeam2= localTeam2
            adapter?.notifyDataSetChanged()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun initList() {
        var item = Item(index = "0", openTeam1 = localTeam1, openTeam2 = localTeam2, closeTeam1 = localTeam1, closeTeam2 = localTeam2)
        dateList.add(item)
    }

    private fun initRecycleView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView?.layoutManager = layoutManager

        // 设置PagerSnapHelper实现整页滑动
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        // 禁用过度滚动效果
        recyclerView?.overScrollMode = View.OVER_SCROLL_NEVER


        adapter = MyAdapter(dateList)
        recyclerView?.adapter = adapter

        // 添加页面切换监听
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    currentPosition = layoutManager.findFirstCompletelyVisibleItemPosition()
                    if (currentPosition != RecyclerView.NO_POSITION) {
                        Log.i(
                            TAG, "position $currentPosition, data= ${
                                dateList?.get(currentPosition).toString()
                            }"
                        )
                        index?.text = "第 ${currentPosition + 1} 局"
                        adapter?.notifyDataSetChanged()
                    }
                }
            }
        })
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)
        start = findViewById(R.id.tv_start)
        result = findViewById(R.id.tv_result)
        delete = findViewById(R.id.iv_delete)
        next = findViewById(R.id.iv_next)
        index = findViewById(R.id.tv_index)

        start?.setOnClickListener {
            handleScore(dateList)
        }
        next?.setOnClickListener {
            var item = Item(index = "${dateList.size}", openTeam1 = localTeam1, openTeam2 = localTeam2, closeTeam1 = localTeam1, closeTeam2 = localTeam2)
            dateList.add(item)

            for (d in dateList) {
                Log.i(TAG, "d = ${d.toString()}")
            }

            recyclerView?.smoothScrollToPosition(dateList.size - 1)
            adapter?.notifyDataSetChanged()
            index?.text = "第 ${dateList.size - 1} 局"
        }

        delete?.setOnClickListener {
            Log.i(TAG, "delete : dateList ${dateList.size}")
            if (dateList.size > 1) {
                dateList.removeAt(currentPosition)
                adapter?.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "最后一个不可删除", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleScore(dataList: List<Item>?) {
        var team1 = 0
        var team2 = 0
        for (list in dataList!!) {
            Log.i(TAG, "list = ${list.realResultScore}, ${list.realWinTeam}")
            if (list.realWinTeam == localTeam1) {
                team1 += list.realResultScore
            } else if (list.realWinTeam == localTeam2) {
                team2 += list.realResultScore
            } else {
                Log.i(TAG, "default")
            }
        }
        result?.text = "1队 ： $team1 分， 2队： $team2 分"
    }
}