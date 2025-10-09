package com.duidiao.cf

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.duidiao.cf.adapter.CountManagerAdapter
import com.duidiao.cf.adapter.MyAdapter
import com.duidiao.cf.model.CountManagerItem
import com.duidiao.cf.model.Item
import com.duidiao.cf.presenter.CountManagerPresenter

class MainActivity : ComponentActivity() {

    private val TAG = "MainActivity"
    private var recyclerView: RecyclerView? = null
    private var countManagerRecyclerView: RecyclerView? = null
    private val playDateList = mutableListOf<Item>()
    private val countManagerDateList = mutableListOf<CountManagerItem>()
    private var start: TextView? = null
    private var result: TextView? = null
    private var index: TextView? = null
    private var next: ImageView? = null
    private var delete: ImageView? = null
    private var rlPlay: RelativeLayout? = null
    private var rlCountManager: RelativeLayout? = null
    private var ivPlay: ImageView? = null
    private var tvPlay: TextView? = null
    private var ivCountManager: ImageView? = null
    private var tvCountManager: TextView? = null
    private var scScore: ScrollView? = null
    private var llCountManager: RelativeLayout? = null
    private var countManagerCreate: ImageView? = null

    private var currentPosition = 0
    private var adapter: MyAdapter? = null
    private var countManagerAdapter: CountManagerAdapter? = null
    private var localTeam1:String = ""
    private var localTeam2:String = ""
    private var countManagerPresenter: CountManagerPresenter? = null
    private var isFinished = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initView()
        initList()
        initRecycleView()
        initCountManagerRecycleView()
        showDialog()
        countManagerPresenter = CountManagerPresenter(this)
    }

    private fun initCountManagerRecycleView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        countManagerRecyclerView?.layoutManager = layoutManager
        countManagerAdapter = CountManagerAdapter(countManagerDateList)
        countManagerRecyclerView?.adapter = countManagerAdapter
    }

    private fun showDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog_view, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_layout_bg_shape)
        val team1 = dialogView.findViewById<EditText>(R.id.team1)
        val team2 = dialogView.findViewById<EditText>(R.id.team2)
        val save = dialogView.findViewById<TextView>(R.id.save)

        save.setOnClickListener {
            val team1 = team1.text.trim().toString()
            val team2 = team2.text.trim().toString()
            if (team1.isEmpty()) {
                Toast.makeText(this, "战队1 名称不能为空", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (team2.isEmpty()) {
                Toast.makeText(this, "战队2 名称不能为空", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (team1 == team2) {
                Toast.makeText(this, "战队名称不能相同", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (team1.length > 6 || team2.length > 6){
                Toast.makeText(this, "战队名称限定6个字以内", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Log.i(TAG, "showDialog: team1 = $team1 , team2 = $team2, size = ${countManagerDateList.size}")
            if (!isFinished && localTeam1.isNotEmpty() && localTeam2.isNotEmpty()) {
                Toast.makeText(this, "其他战队比赛还未结束", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            localTeam1 = team1
            localTeam2 = team2
            playDateList.clear()
            var item = Item(index = "0", openTeam1 = localTeam1, openTeam2 = localTeam2, closeTeam1 = localTeam1, closeTeam2 = localTeam2)
            playDateList.add(item)
            adapter?.update(playDateList)
            createCountManagerDate(localTeam1)
            createCountManagerDate(localTeam2)
            clearCache()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun clearCache() {
        result?.text = ""
        isFinished = false
        index?.text = "第 1 局"
    }

    private fun createCountManagerDate(team: String) {
        for (listItem in countManagerDateList) {
            if (listItem.teamName == team) {
                return
            }
        }
        var countManagerItem = CountManagerItem(teamName = team)
        countManagerDateList.add(countManagerItem)
        Log.i(TAG, "createCountManagerDate: countManagerDateList size = ${countManagerDateList.size}")
        countManagerAdapter?.updateData(countManagerDateList)
    }

    private fun initList() {
        var item = Item(index = "0", openTeam1 = localTeam1, openTeam2 = localTeam2, closeTeam1 = localTeam1, closeTeam2 = localTeam2)
        playDateList.add(item)
    }

    private fun initRecycleView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView?.layoutManager = layoutManager

        // 设置PagerSnapHelper实现整页滑动
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        // 禁用过度滚动效果
        recyclerView?.overScrollMode = View.OVER_SCROLL_NEVER


        adapter = MyAdapter(playDateList)
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
                                playDateList?.get(currentPosition).toString()
                            }"
                        )
                        index?.text = "第 ${currentPosition + 1} 局"
                        adapter?.update(playDateList)
                    }
                }
            }
        })
    }

    @SuppressLint("ResourceAsColor")
    private fun initView() {
        Log.i(TAG, "initView")
        recyclerView = findViewById(R.id.recyclerView)
        start = findViewById(R.id.tv_start)
        result = findViewById(R.id.tv_result)
        delete = findViewById(R.id.iv_delete)
        next = findViewById(R.id.iv_next)
        index = findViewById(R.id.tv_index)
        rlPlay = findViewById(R.id.rl_play)
        rlCountManager = findViewById(R.id.rl_count_manager)
        ivPlay = findViewById(R.id.iv_play)
        tvPlay = findViewById(R.id.tv_play)
        ivCountManager = findViewById(R.id.iv_count_manager)
        tvCountManager = findViewById(R.id.tv_count_manager)
        scScore = findViewById(R.id.sc_score)
        llCountManager = findViewById(R.id.ll_count_manager)
        countManagerRecyclerView = findViewById(R.id.recycler_count_manager)
        countManagerCreate = findViewById(R.id.iv_count_manager_create)

        start?.setOnClickListener {
            showEnterDialog()
        }
        next?.setOnClickListener {

            if (isFinished) {
                Toast.makeText(this, "对局结束，无法操作", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var item = Item(index = "${playDateList.size}", openTeam1 = localTeam1, openTeam2 = localTeam2, closeTeam1 = localTeam1, closeTeam2 = localTeam2)
            playDateList.add(item)

            for (d in playDateList) {
                Log.i(TAG, "d = ${d.toString()}")
            }

            recyclerView?.smoothScrollToPosition(playDateList.size - 1)
            adapter?.update(playDateList)
            index?.text = "第 ${playDateList.size - 1} 局"
        }

        delete?.setOnClickListener {
            Log.i(TAG, "delete : dateList ${playDateList.size}")
            if (isFinished) {
                Toast.makeText(this, "对局结束，无法操作", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (playDateList.size > 1) {
                playDateList.removeAt(currentPosition)
                adapter?.update(playDateList)
            } else {
                Toast.makeText(this, "最后一个不可删除", Toast.LENGTH_SHORT).show()
            }
        }

        rlPlay?.setOnClickListener{
            ivPlay?.setImageResource(R.drawable.ic_play_select)
            tvPlay?.setTextColor(getColor(R.color.bottom_bar_select_color))
            ivCountManager?.setImageResource(R.drawable.ic_count_manager_normal)
            tvCountManager?.setTextColor(getColor(R.color.bottom_bar_normal_color))
            scScore?.visibility = View.VISIBLE
            llCountManager?.visibility = View.GONE
        }

        rlCountManager?.setOnClickListener {
            ivPlay?.setImageResource(R.drawable.ic_play_normal)
            tvPlay?.setTextColor(getColor(R.color.bottom_bar_normal_color))
            ivCountManager?.setImageResource(R.drawable.ic_count_manager_select)
            tvCountManager?.setTextColor(getColor(R.color.bottom_bar_select_color))
            scScore?.visibility = View.GONE
            llCountManager?.visibility = View.VISIBLE
        }

        countManagerCreate?.setOnClickListener {
            Log.i(TAG, "initView: countManagerCreate")
            showDialog()
        }
    }

    private fun handleScore(dataList: List<Item>?) {
        var team1 = 0.0f
        var team2 = 0.0f
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
        Log.i(TAG, "team1 = $team1, team2 = $team2")
        if (team1 >= team2) {
            var team1Result = ((team1 - team2) / 2)
            Log.i(TAG, "handleScore: team1Result = $team1Result")
            if (team1Result + 10 > 20){
                team1Result = 20.0f
            } else {
                team1Result += 10
            }
            team1 = team1Result
            team2 = 20 - team1
            for (listItem in countManagerDateList) {
                if (localTeam1 == listItem.teamName) {
                    listItem.teamScore += team1
                }
                if (localTeam2 == listItem.teamName) {
                    listItem.teamScore += team2
                }
            }
        } else {
            var team2Result = ((team2 - team1) / 2)
            Log.i(TAG, "handleScore: team2Result = $team2Result")
            if (team2Result + 10 > 20){
                team2Result = 20.0f
            } else {
                team2Result += 10
            }
            team2 = team2Result
            team1 = 20 - team2

            for (listItem in countManagerDateList) {
                if (localTeam1 == listItem.teamName) {
                    listItem.teamScore += team1
                }
                if (localTeam2 == listItem.teamName) {
                    listItem.teamScore += team2
                }
            }
        }
        countManagerAdapter?.updateData(countManagerDateList)

        result?.text = "$localTeam1:$team1 分,${localTeam2}:$team2 分"
        isFinished = true
    }

    private fun showEnterDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.total_enter_dialog_view, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)

        val dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_layout_bg_shape)
        val cancel = dialogView.findViewById<TextView>(R.id.cancel)
        val save = dialogView.findViewById<TextView>(R.id.save)

        save.setOnClickListener {
            for (itemList in playDateList) {
                if (!itemList.isFinished) {
                    Toast.makeText(this, "还有对局未结束，无法操作", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            handleScore(playDateList)
            dialog.dismiss()
        }
        cancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}