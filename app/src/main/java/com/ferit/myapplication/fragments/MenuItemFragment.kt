package com.ferit.myapplication.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.ferit.myapplication.CartActivity
import com.ferit.myapplication.R
import com.ferit.myapplication.dataclasses.FoodListItem
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.fragment_menu_item.*
import kotlinx.android.synthetic.main.fragment_menu_item.btnBack
import kotlinx.android.synthetic.main.fragment_menu_item.ivFoodImage
import kotlinx.android.synthetic.main.rvelement_foods.*
import kotlinx.android.synthetic.main.rvelement_foods.view.*

class MenuItemFragment() : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_item, container, false)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvFoodItemName.text = arguments?.getString("foodName")
        tvFoodDescription.text = arguments?.getString("foodDescription")

        val foodPrice = arguments?.getFloat("foodPrice")
        val foodImage = arguments?.getString("foodImage")
        val preparationTime = arguments?.getInt("foodPreparation")

        Glide
            .with(this)
            .load(foodImage)
            .into(ivFoodImage)


        btnBack.setOnClickListener{
            val fragmentTransaction: FragmentTransaction? =
                activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fvMainActivity, MenuListFragment())
            fragmentTransaction?.commit()
        }

        btnAddFoodItem.setOnClickListener {

            if(etFoodItemAmount.text.isNotEmpty()) {
                if(etFoodItemAmount.text.toString().toFloat() > 0) {
                    val intent = Intent(context, CartActivity()::class.java)
                    intent.putExtra("foodName", tvFoodItemName.text)
                    intent.putExtra("foodImage", foodImage)
                    intent.putExtra("foodPrice", foodPrice.toString())
                    intent.putExtra("foodAmount", etFoodItemAmount.text.toString())
                    intent.putExtra("foodDescription", tvFoodDescription.text)
                    intent.putExtra("foodPreparation", preparationTime.toString())
                    context?.startActivity(intent)
                } else {
                    Toast.makeText(context, "Enter Valid Amount", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Enter Amount", Toast.LENGTH_SHORT).show()
            }

        }

    }


}
