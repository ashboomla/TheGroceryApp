package com.example.thegroceryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.thegroceryapp.R
import com.example.thegroceryapp.adapters.AdapterCategory
import com.example.thegroceryapp.adapters.AdapterViewPagerImageSlider
import com.example.thegroceryapp.appData.Endpoints
import com.example.thegroceryapp.models.Category
import com.example.thegroceryapp.models.CategoryResponse
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mList: ArrayList<Category> = ArrayList() //Create an arrayList of the Category DC

    lateinit var adapterCategory: AdapterCategory // lateinit = late initizilation : has no value until you later initialize it

    internal lateinit var viewPager: ViewPager //?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById<View>(R.id.view_pager) as ViewPager
        val adapter = AdapterViewPagerImageSlider(this)
        viewPager.adapter = adapter

        init()
    }

    private fun init() {
        getData()
        recycler_view.layoutManager =
            GridLayoutManager(this, 2) //init Layout Manager : picks layout  // mContext = this ; this lets the adapter know that this current
        adapterCategory = AdapterCategory(
            this,
            mList
        )
        // activty is using the adapter for the recycle view

        recycler_view.adapter = adapterCategory //set the adapter to the recycler view

        button_logout_Main.setOnClickListener{
            var sharedPreferences = getSharedPreferences("my_login", Context.MODE_PRIVATE)
            var editor = sharedPreferences.edit()
            editor.clear() // clears the file
            startActivity(Intent(this,LaunchActivity::class.java))
        }
    }

    private fun getData() {
        val url = "https://apolis-grocery.herokuapp.com/api/category/"

        var requestQueue =
            Volley.newRequestQueue(this) //Request Queue keeps track of all your requests

        var stringRequest = StringRequest(
            Request.Method.GET, Endpoints.getCategory(),
            Response.Listener {
                Log.d("data", it.toString()) // store it in log

                var gson = GsonBuilder().create() //creates an instance of gson
                var categoryResponse = gson.fromJson(
                    it.toString(),
                    CategoryResponse::class.java
                )

                mList = categoryResponse.data
               // Log.d("Category",mList[0].toString()) //debug check
                adapterCategory.setData(mList) // passing the new model class over the layout to overwrite and display it .
                progress_bar.visibility = View.GONE
            },
            Response.ErrorListener {
                Log.e("data", it.message)
            })

        requestQueue.add(stringRequest) //adding request to another object
    }
}
