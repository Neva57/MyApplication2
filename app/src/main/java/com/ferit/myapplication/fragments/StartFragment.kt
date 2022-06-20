package com.ferit.myapplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.ferit.myapplication.R
import com.ferit.myapplication.dataclasses.FoodList
import com.ferit.myapplication.interfaces.FoodApiEndPoints
import com.ferit.myapplication.objects.ServiceBuilder
import kotlinx.android.synthetic.main.fragment_start.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StartFragment : Fragment() {

    lateinit var listFoodItems : FoodList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_start, container, false)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        btnStart.setOnClickListener{
            val nextFragment = MenuListFragment()

            val fragmentTransaction: FragmentTransaction? =
                activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fvMainActivity, nextFragment)
            fragmentTransaction?.commit()

        }
    }





}
