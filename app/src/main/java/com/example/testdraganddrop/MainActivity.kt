package com.example.testdraganddrop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testdraganddrop.adapters.ParentAdapter
import com.example.testdraganddrop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ParentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        adapter = ParentAdapter()
        adapter.setData(testData(), this, DragListener())
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recycler.layoutManager = layoutManager
        binding.recycler.adapter = adapter

    }

    fun testData() : List<ItemParent>{
        var list = arrayListOf<ItemParent>()
        var current = 1
        for (x in 1..5){
            var item = ItemParent()
            item.id = x
            item.title = "Columna $x"
            for (y in 1..4){
                var child = ItemParent()
                child.id = x
                child.title = "Tarea $current"
                child.subtitle = "Subtitle child $current"
                item.children?.add(child)
                current++
            }
            list.add(item)
        }
        return list
    }
}