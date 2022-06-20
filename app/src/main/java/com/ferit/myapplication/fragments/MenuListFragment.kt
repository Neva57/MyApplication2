package com.ferit.myapplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ferit.myapplication.R
import com.ferit.myapplication.adapters.FoodListAdapter
import com.ferit.myapplication.dataclasses.FoodList
import com.ferit.myapplication.dataclasses.FoodListItem
import com.ferit.myapplication.interfaces.FoodApiEndPoints
import com.ferit.myapplication.objects.ServiceBuilder
import kotlinx.android.synthetic.main.fragment_menu_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MenuListFragment( ) : Fragment() {

    lateinit var listFoodItems: FoodList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_list, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        callApi()
    }

    private fun callApi() {
        val request = ServiceBuilder.buildService(FoodApiEndPoints::class.java)
        val call = request.getMenu()

        call.enqueue(object : Callback<FoodList> {
            override fun onResponse(call: Call<FoodList>, response: Response<FoodList>) {
                if(response.isSuccessful) {
                    listFoodItems = response.body()!!

                    rvFoodList.apply{
                        layoutManager = LinearLayoutManager(this.context)
                        adapter = FoodListAdapter(listFoodItems)
                    }

                }
            }
            override fun onFailure(call: Call<FoodList>, t: Throwable)
            {
                Log.d("FAILED", t.message.toString())
            }
        })
    }


}
