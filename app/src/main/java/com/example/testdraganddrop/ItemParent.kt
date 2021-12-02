package com.example.testdraganddrop

class ItemParent (
    var id: Int = 0,
    var title: String = "",
    var subtitle: String? = "",
    var children: ArrayList<ItemParent> = arrayListOf()
)