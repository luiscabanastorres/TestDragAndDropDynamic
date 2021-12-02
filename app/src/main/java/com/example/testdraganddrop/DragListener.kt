package com.example.testdraganddrop

import android.graphics.Color
import android.util.Log
import android.view.DragEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.testdraganddrop.adapters.ChildAdapter
import com.example.testdraganddrop.adapters.ParentAdapter

class DragListener: View.OnDragListener {
    private var isDropped = false
    var other = true

    override fun onDrag(v: View?, event: DragEvent?): Boolean {
        val action = event!!.action
        val viewId = v!!.id
        val viewSource = event.localState as View
        var positionTarget = -1

        when(action){
            DragEvent.ACTION_DRAG_STARTED -> {

            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                Log.i("Position", "X=${v.x} Y=${v.y}")
                //v.setBackgroundColor(Color.LTGRAY);
                /*
                var target: RecyclerView? = null
                when (viewId) {
                    R.id.tvEmptyListTop -> {
                        other = true
                        var viewww = v.parent as View
                        target = viewww!!.findViewById<View>(R.id.recyclerChild) as RecyclerView
                    }
                    R.id.recyclerChild -> {
                        other = true
                        var viewww = v.parent as View
                        target = viewww!!.findViewById<View>(R.id.recyclerChild) as RecyclerView
                        /*     target =
                                 v!!.rootView.findViewById<View>(R.id.recyclerChild) as RecyclerView*/
                    }
                    else -> {
                        other = false
                        target = v.parent.parent as RecyclerView
                        positionTarget = v.tag as Int
                    }
                }
                val sourceChild = viewSource.parent.parent as RecyclerView
                if(sourceChild.tag != target.tag) {
                    if (viewSource != null) {
                        var viewParent = v.parent.parent.parent as View
                        var recyclerParent = viewParent!!.findViewById<View>(R.id.recycler) as RecyclerView

                        var position = target.tag
                        if((target.tag as Int) < (sourceChild.tag as Int)){
                            recyclerParent.scrollToPosition((position  as Int) - 1)
                        }else{
                            recyclerParent.scrollToPosition((position  as Int) + 1)
                        }
                    }
                }*/
            }

            DragEvent.ACTION_DRAG_EXITED -> {
                //v.setBackgroundColor(Color.YELLOW);
            }

            DragEvent.ACTION_DROP -> {
                isDropped = true
                var target: RecyclerView? = null
                when (viewId) {
                    R.id.tvEmptyListTop -> {
                        var viewww = v.parent as View
                        target = viewww!!.findViewById<View>(R.id.recyclerChild) as RecyclerView
                    }
                    R.id.recyclerChild -> {
                        var viewww = v.parent as View
                        target = viewww!!.findViewById<View>(R.id.recyclerChild) as RecyclerView
                   /*     target =
                            v!!.rootView.findViewById<View>(R.id.recyclerChild) as RecyclerView*/
                    }
                    else -> {
                        target = v.parent.parent as RecyclerView
                        positionTarget = v.tag as Int
                    }
                }

                if (viewSource != null) {
                    val source = viewSource.parent.parent as RecyclerView
                    val adapterSource: ChildAdapter? = source.adapter as ChildAdapter?
                    val positionSource = viewSource.tag as Int
                    val sourceId = source.id

                    val list: ItemParent = adapterSource!!.getList()[positionSource]
                    val listSource: ArrayList<ItemParent> = arrayListOf()
                    listSource.addAll(adapterSource.getList())

                    listSource.removeAt(positionSource)
                    adapterSource.setData(listSource, this)

                    val adapterTarget: ChildAdapter = target?.adapter as ChildAdapter
                    val customListTarget: ArrayList<ItemParent> = arrayListOf()
                    customListTarget.addAll(adapterTarget.getList())

                    if (positionTarget >= 0) {
                        customListTarget.add(positionTarget, list)
                    } else {
                        customListTarget.add(list)
                    }
                    adapterTarget.setData(customListTarget, this)
                }
            }

            DragEvent.ACTION_DRAG_ENDED -> {

            }
        }
        if (!isDropped && event.localState != null) {
            (event.localState as View).visibility = View.VISIBLE
        }
        return true
    }
}