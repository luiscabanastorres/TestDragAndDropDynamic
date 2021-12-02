package com.example.testdraganddrop.adapters

import android.content.ClipData
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.*
import android.view.View.DragShadowBuilder
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.testdraganddrop.DragListener
import com.example.testdraganddrop.ItemParent
import com.example.testdraganddrop.R

class ChildAdapter  : RecyclerView.Adapter<ChildAdapter.ViewHolder>() {
    lateinit var context: Context
    var items: List<ItemParent> = ArrayList()
    lateinit var dragListener : DragListener
    private lateinit var listener: ParentAdapter.Listener

    fun setListener(listener: ParentAdapter.Listener){
        this.listener = listener
    }

    fun setData(list: List<ItemParent>, dragListener: DragListener) {
        items = list.toMutableList()
        if(items.isEmpty()){
            listener.setEmpty(true)
        }else{
            listener.setEmpty(false)
        }
        this.dragListener = dragListener
        notifyDataSetChanged()
    }

    fun getList() : List<ItemParent>{
        return items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildAdapter.ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView: View = inflater.inflate(R.layout.item_child, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ChildAdapter.ViewHolder, position: Int) {
        var item = items[position]
        with(holder) {
            title.text = item.title
            subtitle.text = item.subtitle
            card.tag = position
  /*          card.setOnTouchListener { v, event ->

                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        val data = ClipData.newPlainText("", "")
                        val shadowBuilder = DragShadowBuilder(v)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            v.startDragAndDrop(data, shadowBuilder, v, 0)
                        } else {
                            v.startDrag(data, shadowBuilder, v, 0)
                        }
                        return@setOnTouchListener true
                    }
                }

                return@setOnTouchListener false
            }*/
            card.setOnLongClickListener {
                val data = ClipData.newPlainText("", "")
                val shadowBuilder = DragShadowBuilder(it)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    it.startDragAndDrop(data, shadowBuilder, it, 0)
                } else {
                    it.startDrag(data, shadowBuilder, it, 0)
                }
                return@setOnLongClickListener true
            }

            card.setOnDragListener(dragListener)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.tv_title)
        var subtitle: TextView = itemView.findViewById(R.id.tv_subtitle)
        var card: CardView = itemView.findViewById(R.id.viewChild)
    }
}