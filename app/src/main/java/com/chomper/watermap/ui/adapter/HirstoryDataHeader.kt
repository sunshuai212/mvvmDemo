package com.chomper.watermap.ui.adapter

import android.graphics.Color
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chomper.watermap.R

class HirstoryDataHeader(data: MutableList<String>?) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_hirstory_data_header, data) {

    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper?.setText(R.id.tv_name,item)
        if (helper!!.adapterPosition % 2 == 1) {
            helper.itemView.setBackgroundColor(Color.parseColor("#F7F7F7"))
        } else {
            helper.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
    }

}