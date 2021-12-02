package com.example.testdraganddrop.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testdraganddrop.DragListener
import com.example.testdraganddrop.ItemParent
import com.example.testdraganddrop.R

class ParentAdapter  : RecyclerView.Adapter<ParentAdapter.ViewHolder>() {
    lateinit var context: Context
    lateinit var dragListener : DragListener
    var items: List<ItemParent> = ArrayList()
    private lateinit var adapter: ChildAdapter

    fun getList() : List<ItemParent>{
        return items
    }

    fun setData(list: List<ItemParent>, context: Context, dragListener: DragListener) {
        this.items = list.toMutableList()
        this.dragListener = dragListener
        this.context = context
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentAdapter.ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView: View = inflater.inflate(R.layout.item_parent, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ParentAdapter.ViewHolder, position: Int) {
        var item = items[position]
        with(holder) {
            title.text = item.title

            adapter = ChildAdapter()
            adapter.setListener(object : Listener{
                override fun setEmpty(visibility: Boolean) {
                    empty.isVisible = visibility
                }
            })
            adapter.setData( item.children, dragListener)
            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            recycler.layoutManager = layoutManager
            recycler.adapter = adapter

            recycler.tag = position
            recycler.setOnDragListener(dragListener)
            empty.isVisible = item.children.size == 0

           // recycler.setOnDragListener(dragListener)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.tv_title)
        var recycler: RecyclerView = itemView.findViewById(R.id.recyclerChild)
        var empty: TextView = itemView.findViewById(R.id.tvEmptyListTop)
    }

    interface Listener{
        fun setEmpty(visibility: Boolean)
    }

}