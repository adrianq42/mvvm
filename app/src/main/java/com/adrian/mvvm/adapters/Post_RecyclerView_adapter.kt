package com.adrian.mvvm.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.mvvm.R
import com.adrian.mvvm.database.PostEntity
import com.adrian.mvvm.utils.inflate
import kotlinx.android.synthetic.main.list_item_post.view.*

class Posts_RecyclerView_adapter (private val items: List<PostEntity>, private val listener: Posts_RecyclerView_listener)
    : RecyclerView.Adapter<Posts_RecyclerView_adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.list_item_post))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount() = items.size


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item: PostEntity, listener: Posts_RecyclerView_listener) = with(itemView){

            txtId.text = item.id.toString()
            txtUserId.text = item.userId.toString()
            txtTitle.text = item.title

            when (item.completed) {
                true ->  txtCompleted.text = "True"
                false -> txtCompleted.text = "False"
            }

            setOnClickListener{ listener.onClick(item, adapterPosition)}

        }
    }

}