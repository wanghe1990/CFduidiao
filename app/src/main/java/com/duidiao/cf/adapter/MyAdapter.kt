package com.duidiao.cf.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.duidiao.cf.R
import com.duidiao.cf.model.Item


// 适配器类（独立文件）
class MyAdapter(private val dataList: List<Item>?) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    // 创建ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleview_item, parent, false)
        return ViewHolder(view)
    }

    // 绑定数据到ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList?.get(position)
        item?.index = "第 $position 局"
        holder.index.text = item?.index.toString()
        holder.openRbNorth.isChecked = item?.openNorthCheckable!!
        holder.openRbEast.isChecked = item?.openEastCheckable!!
        holder.openUseBig1.isSelected = item?.openUseBig1Select!!
        holder.openUseBig2.isSelected = item?.openUseBig2Select!!
        holder.openUseSmall1.isSelected = item?.openUseSmall1Select!!
        holder.openUseSmall2.isSelected = item?.openUseSmall2Select!!
        holder.openDoubleBigKou.isSelected = item?.openDoubleBigKouSelect!!
        holder.openDoubleSmallKou.isSelected = item?.openDoubleSmallKouSelect!!
        holder.openSingleBigKou.isSelected = item?.openSingleBigKouSelect!!
        holder.openSingleSmallKou.isSelected = item?.openSingleSmallKouSelect!!
        holder.openScore.setText(item?.openScore.toString())

        holder.closeRbNorth.isChecked = item?.closeNorthCheckable!!
        holder.closeRbEast.isChecked = item?.closeEastCheckable!!
        holder.closeRbNorth.isClickable = false
        holder.closeRbEast.isClickable = false
        holder.closeUseBig1.isSelected = item?.closeUseBig1Select!!
        holder.closeUseBig2.isSelected = item?.closeUseBig2Select!!
        holder.closeUseSmall1.isSelected = item?.closeUseSmall1Select!!
        holder.closeUseSmall2.isSelected = item?.closeUseSmall2Select!!
        holder.closeDoubleBigKou.isSelected = item?.closeDoubleBigKouSelect!!
        holder.closeDoubleSmallKou.isSelected = item?.closeDoubleSmallKouSelect!!
        holder.closeSingleBigKou.isSelected = item?.closeSingleBigKouSelect!!
        holder.closeSingleSmallKou.isSelected = item?.closeSingleSmallKouSelect!!
        holder.closeScore.setText(item?.closeScore.toString())
        initEvent(holder, item)
    }

    private fun initEvent(holder: ViewHolder, item: Item?) {
        holder.openRbNorth.setOnCheckedChangeListener { button, isChecked ->
            item?.openNorthCheckable = isChecked
            item?.closeNorthCheckable = !isChecked
            holder.closeRbNorth.isChecked = !isChecked
            holder.closeRbEast.isChecked = isChecked
            if (isChecked) {
                item?.openTeam1 = button.text.toString()
                item?.openTeam2 = holder.openRbEast.text.toString()
            }
        }

        holder.openRbEast.setOnCheckedChangeListener { button, isChecked ->
            item?.openEastCheckable = isChecked
            item?.closeEastCheckable = !isChecked
            holder.closeRbEast.isChecked = !isChecked
            holder.closeRbNorth.isChecked = isChecked
            if (isChecked) {
                item?.openTeam1 = button.text.toString()
                item?.openTeam2 = holder.openRbNorth.text.toString()
            }
        }
        holder.openDoubleBigKou.setOnCheckedChangeListener { button, isChecked ->
            item?.openDoubleBigKouSelect = isChecked
        }
        holder.openDoubleSmallKou.setOnCheckedChangeListener { button, isChecked ->
            item?.openDoubleSmallKouSelect = isChecked
        }
        holder.openSingleBigKou.setOnCheckedChangeListener { button, isChecked ->
            item?.openSingleBigKouSelect = isChecked
        }
        holder.openSingleSmallKou.setOnCheckedChangeListener { button, isChecked ->
            item?.openSingleSmallKouSelect = isChecked
        }

        holder.openUseBig1.setOnCheckedChangeListener { button, isChecked ->
            item?.openUseBig1Select = isChecked
        }
        holder.openUseBig2.setOnCheckedChangeListener { button, isChecked ->
            item?.openUseBig2Select = isChecked
        }
        holder.openUseSmall1.setOnCheckedChangeListener { button, isChecked ->
            item?.openUseSmall1Select = isChecked
        }
        holder.openUseSmall2.setOnCheckedChangeListener { button, isChecked ->
            item?.openUseSmall2Select = isChecked
        }

        holder.openScore.addTextChangedListener {object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                item?.openScore = p0.toString().toInt()
            }
        }}

        //close
        holder.closeDoubleBigKou.setOnCheckedChangeListener { button, isChecked ->
            item?.closeDoubleBigKouSelect = isChecked
        }
        holder.closeDoubleSmallKou.setOnCheckedChangeListener { button, isChecked ->
            item?.closeDoubleSmallKouSelect = isChecked
        }
        holder.closeSingleBigKou.setOnCheckedChangeListener { button, isChecked ->
            item?.closeSingleBigKouSelect = isChecked
        }
        holder.closeSingleSmallKou.setOnCheckedChangeListener { button, isChecked ->
            item?.closeSingleSmallKouSelect = isChecked
        }

        holder.closeUseBig1.setOnCheckedChangeListener { button, isChecked ->
            item?.closeUseBig1Select = isChecked
        }
        holder.closeUseBig2.setOnCheckedChangeListener { button, isChecked ->
            item?.closeUseBig2Select = isChecked
        }
        holder.closeUseSmall1.setOnCheckedChangeListener { button, isChecked ->
            item?.closeUseSmall1Select = isChecked
        }
        holder.closeUseSmall2.setOnCheckedChangeListener { button, isChecked ->
            item?.closeUseSmall2Select = isChecked
        }
        holder.closeScore.addTextChangedListener {object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                item?.closeScore = p0.toString().toInt()
            }
        }}
    }

    // 返回数据项数量
    override fun getItemCount(): Int = dataList?.size!!

    // ViewHolder类
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val index: TextView = itemView.findViewById(R.id.tv_index)
        val openRadioGroup: RadioGroup = itemView.findViewById(R.id.rg_open)
        val openRbNorth: RadioButton = itemView.findViewById(R.id.rb_open_north) //东西
        val openRbEast: RadioButton = itemView.findViewById(R.id.rb_open_east) //南北
        val openUseBig1: CheckBox = itemView.findViewById(R.id.tv_open_use_big1)
        val openUseBig2: CheckBox = itemView.findViewById(R.id.tv_open_use_big2)
        val openUseSmall1: CheckBox = itemView.findViewById(R.id.tv_open_use_small1)
        val openUseSmall2: CheckBox = itemView.findViewById(R.id.tv_open_use_small2)
        val openLL: LinearLayout = itemView.findViewById(R.id.ll_open)
        val openDoubleBigKou: CheckBox = itemView.findViewById(R.id.tv_open_double_big)
        val openDoubleSmallKou: CheckBox = itemView.findViewById(R.id.tv_open_double_small)
        val openSingleBigKou: CheckBox = itemView.findViewById(R.id.tv_open_single_big)
        val openSingleSmallKou: CheckBox = itemView.findViewById(R.id.tv_open_single_small)
        val openSubImage: ImageView = itemView.findViewById(R.id.iv_open_sub)
        val openScore: EditText = itemView.findViewById(R.id.et_open_score)
        val openAddImage: ImageView = itemView.findViewById(R.id.iv_open_add)

        val closeRbNorth: RadioButton = itemView.findViewById(R.id.rb_close_north) //东西
        val closeRbEast: RadioButton = itemView.findViewById(R.id.rb_close_east) //南北
        val closeUseBig1: CheckBox = itemView.findViewById(R.id.tv_close_use_big1)
        val closeUseBig2: CheckBox = itemView.findViewById(R.id.tv_close_use_big2)
        val closeUseSmall1: CheckBox = itemView.findViewById(R.id.tv_close_use_small1)
        val closeUseSmall2: CheckBox = itemView.findViewById(R.id.tv_close_use_small2)
        val closeLL: LinearLayout = itemView.findViewById(R.id.ll_close)
        val closeDoubleBigKou: CheckBox = itemView.findViewById(R.id.tv_close_double_big)
        val closeDoubleSmallKou: CheckBox = itemView.findViewById(R.id.tv_close_double_small)
        val closeSingleBigKou: CheckBox = itemView.findViewById(R.id.tv_close_single_big)
        val closeSingleSmallKou: CheckBox = itemView.findViewById(R.id.tv_close_single_small)
        val closeSubImage: ImageView = itemView.findViewById(R.id.iv_close_sub)
        val closeScore: EditText = itemView.findViewById(R.id.et_close_score)
        val closeAddImage: ImageView = itemView.findViewById(R.id.iv_close_add)
    }
}