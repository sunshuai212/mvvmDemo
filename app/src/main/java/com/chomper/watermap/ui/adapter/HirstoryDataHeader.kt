package com.chomper.watermap.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chomper.watermap.R

class HirstoryDataHeader(data: MutableList<String>?) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_hirstory_data_header, data) {

    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper?.setText(R.id.tv_name,item)
    }

}