

package com.ali.moviesApp.main.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


interface Diffable {
    fun isSameItem(other: Diffable): Boolean
    fun hasSameContent(other: Diffable): Boolean
}

abstract class CustomBaseAdapter<T : Diffable, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    private var dataList: List<T> = emptyList()

    fun setData(newDataList: List<T>) {
        val diffResult = DiffUtil.calculateDiff(object : DataDiffCallback<T>(dataList, newDataList) {
            override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem.isSameItem(newItem)
            override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem.hasSameContent(newItem)
        })
        dataList = newDataList
        diffResult.dispatchUpdatesTo(this)
    }

    fun addItem(item: T) {
        dataList = dataList + item
        notifyItemInserted(dataList.size - 1)
    }

    fun addItems(items: List<T>) {
        val insertPosition = dataList.size
        dataList = dataList + items
        notifyItemRangeInserted(insertPosition, items.size)
    }

    fun updateItem(position: Int, item: T) {
        dataList = dataList.toMutableList().apply { set(position, item) }
        notifyItemChanged(position)
    }

    fun removeItem(position: Int) {
        dataList = dataList.toMutableList().apply { removeAt(position) }
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int = dataList.size

    protected fun getItem(position: Int): T = dataList[position]

    private abstract class DataDiffCallback<T : Diffable>(
        private val oldList: List<T>,
        private val newList: List<T>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return areItemsTheSame(oldList[oldItemPosition], newList[newItemPosition])
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return areContentsTheSame(oldList[oldItemPosition], newList[newItemPosition])
        }

        abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean
        abstract fun areContentsTheSame(oldItem: T, newItem: T): Boolean
    }
}