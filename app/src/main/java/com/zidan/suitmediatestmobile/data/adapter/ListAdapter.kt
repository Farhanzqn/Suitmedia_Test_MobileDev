package com.zidan.suitmediatestmobile.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zidan.suitmediatestmobile.data.response.DataItem
import com.zidan.suitmediatestmobile.databinding.ItemListBinding

class ListAdapter(
    private val onClickAction: (String) -> Unit
) : PagingDataAdapter<DataItem,ListAdapter.ListViewHolder>(DIFF_CALLBACK) {
    override fun onBindViewHolder(holder: ListAdapter.ListViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
            holder.itemView.setOnClickListener {
                onClickAction("${data.firstName} ${data.lastName}")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ListViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater
            .from(parent.context),parent,false)
        return ListViewHolder(binding)
    }
    class ListViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItem) {
            Glide.with(this.itemView.context)
                .load(data.avatar)
                .circleCrop()
                .into(binding.ivPlace)
            binding.tvName.text = "${data.firstName} ${data.lastName}"
            binding.tvEmail.text = data.email
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.email == newItem.email
            }
        }
    }
}