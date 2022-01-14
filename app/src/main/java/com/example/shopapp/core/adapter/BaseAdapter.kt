package com.example.shopapp.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.presentation.adapter.listeners.BaseItemListener

class BaseAdapter<T, L : BaseItemListener>(
    private val idLayout: Int,
    private val listener: L,
    private val bind: ((View, data: T, listener: L) -> Unit)
) : RecyclerView.Adapter<BaseAdapter<T, L>.BaseViewHolder>() {

    private var data: List<T> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder(LayoutInflater.from(parent.context).inflate(idLayout, parent, false))

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(data[position], listener)
    }

    override fun getItemCount(): Int = data.size

    inner class BaseViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: T, listener: L) {
            bind(view, data, listener)
        }
    }

    fun setData(data: List<T>) {
        this.data = data
        notifyDataSetChanged()
    }
}

