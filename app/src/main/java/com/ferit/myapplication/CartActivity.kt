package com.ferit.myapplication

import android.content.Context
import android.icu.util.BuddhistCalendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.ferit.myapplication.fragments.MenuItemFragment
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        btnBack.setOnClickListener{
            finish()
        }

        var foodName = intent.getStringExtra("foodName").toString()
        var foodAmount = intent.getStringExtra("foodAmount").toString().toInt()
        var foodPrice = intent.getStringExtra("foodPrice").toString().toFloat()
        var foodImage = intent.getStringExtra("foodImage").toString()
        var foodPreparationInMin = intent.getStringExtra("foodPreparation").toString().toInt()


        setupData(foodName, foodAmount, foodPrice, foodImage)

        btnOrder.setOnClickListener{
            Toast.makeText(this, " Order Placed", Toast.LENGTH_SHORT).show()

            foodPreparationInMin*=60000
            val timer = object : CountDownTimer(foodPreparationInMin.toLong(), 1000) {
                override fun onTick(p0: Long) {
                    var sec : Int = (p0 / 1000).toInt()
                    var min : Int = sec / 60
                    sec = sec % 60
                    tvCountDown.text = String.format("%02d:%02d", min, sec)
                }

                override fun onFinish() { }
            }

            timer.start()
        }

    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
    }

    fun setupData(foodName: String, foodAmount: Int, foodPrice: Float, foodImage: String) {
        tvFoodName.text = foodName
        tvFoodAmount.text = foodAmount.toString()
        tvPrice.text = foodPrice.toString() + " kn"
        tvTotalPrice.text = (foodAmount * foodPrice).toString() + " kn"

        Glide
            .with(this)
            .load(foodImage)
            .into(ivFoodImage)
    }


}
