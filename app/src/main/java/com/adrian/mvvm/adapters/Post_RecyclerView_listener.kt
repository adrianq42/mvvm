package com.adrian.mvvm.adapters

import com.adrian.mvvm.database.PostEntity

interface Posts_RecyclerView_listener{
    fun onClick(item: PostEntity, position: Int)
}