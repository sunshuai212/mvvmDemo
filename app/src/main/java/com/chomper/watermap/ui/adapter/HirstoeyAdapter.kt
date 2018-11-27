package com.chomper.watermap.ui.adapter

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chomper.watermap.R
import me.goldze.mvvmhabit.binding.viewadapter.recyclerview.DividerLine

class HirstoeyAdapter(data: MutableList<ArrayList<String>>?) :
    BaseQuickAdapter<ArrayList<String>, BaseViewHolder>(R.layout.item_hirstory_data_layout, data) {

    override fun convert(helper: BaseViewHolder?, itemList: ArrayList<String>?) {
        if (helper!!.adapterPosition % 2 == 1) {
            helper.itemView.setBackgroundColor(Color.parseColor("#F7F7F7"))
        } else {
            helper.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
        val container = helper.getView<LinearLayout>(R.id.container)
        container?.removeAllViews()
        val params = LinearLayout.LayoutParams(DividerLine.dip2px(mContext, 100f), DividerLine.dip2px(mContext, 35f))
        params.gravity = Gravity.CENTER
        for (item in itemList!!) {
            container?.addView(View.inflate(mContext, R.layout.item_hirstory_data_header, null).apply {
                if (this is TextView) {
                    text = item
                }
            }, params)
        }

    }
}