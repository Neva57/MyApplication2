package com.ferit.myapplication.interfaces

import com.ferit.myapplication.dataclasses.FoodList
import retrofit2.Call
import retrofit2.http.GET

interface FoodApiEndPoints {
    @GET("list")
    fun getMenu() : Call<FoodList>
}

