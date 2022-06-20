package com.ferit.myapplication.dataclasses

data class FoodListItem(
    val id: String,
    val image: String,
    val ingredients: String,
    val name: String,
    val price: Int,
    val preparationTime: Int
)
