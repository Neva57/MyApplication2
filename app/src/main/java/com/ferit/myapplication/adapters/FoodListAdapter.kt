package com.ferit.myapplication.adapters

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
//import com.bumptech.glide.Glide
import com.ferit.myapplication.R
import com.ferit.myapplication.dataclasses.FoodList
import com.ferit.myapplication.dataclasses.FoodListItem
import com.ferit.myapplication.fragments.MenuItemFragment
import kotlinx.android.synthetic.main.fragment_menu_list.view.*
import kotlinx.android.synthetic.main.rvelement_foods.view.*

class FoodListAdapter (private var foods: FoodList) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FoodItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rvelement_foods, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        when(holder) {
            is FoodItemViewHolder -> {
                holder.bind(foods[position])

                holder.itemView.setOnClickListener(object  :View.OnClickListener{
                    override fun onClick(v: View?) {

                        val activity = v!!.context as AppCompatActivity
                        val fragment = MenuItemFragment()
                        val bundle = Bundle()
                        bundle.putString("foodName", foods[position].name)
                        bundle.putString("foodId", foods[position].id)
                        bundle.putString("foodDescription", foods[position].ingredients)
                        bundle.putString("foodImage", foods[position].image)
                        bundle.putFloat("foodPrice", foods[position].price.toFloat())
                        bundle.putInt("foodPreparation", foods[position].preparationTime)
                        fragment.arguments = bundle
                        val fragmentTransaction: FragmentTransaction =
                            activity.supportFragmentManager?.beginTransaction()
                        fragmentTransaction?.replace(R.id.fvMainActivity, fragment)
                        fragmentTransaction?.commit()
                    }
                })
            }
        }
    }

    override fun getItemCount(): Int {
        return foods.size
    }


    class FoodItemViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(food: FoodListItem) {

            itemView.tvFoodName.text = food.name
            itemView.tvFoodPrice.text = food.price.toString() + " kn"

            Glide
                .with(itemView.context)
                .load(food.image)
                .into(itemView.ivFoodPhoto)


        }
    }
}