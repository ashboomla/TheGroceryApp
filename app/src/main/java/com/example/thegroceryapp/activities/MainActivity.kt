package com.example.thegroceryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.thegroceryapp.R
import com.example.thegroceryapp.adapters.AdapterCategory
import com.example.thegroceryapp.appData.Endpoints
import com.example.thegroceryapp.helpers.SessionManager
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

        ISinit() // initialize imageSlider
        init() // initialize all else
    }

    private fun ISinit() { //imageSlider Init
        val imageList = ArrayList<SlideModel>()
// imageList.add(SlideModel("String Url" or R.drawable, "title", true) Also you can add centerCrop scaleType for this image //INSTRUCTIONS!!!
        imageList.add(
            SlideModel(
                "https://www.blessthismessplease.com/wp-content/uploads/2019/04/how-to-store-potatoes-1.jpg",
                true
            )
        )
        imageList.add(
            SlideModel(
                "https://i.insider.com/5c7819fbeb3ce82ac538ea33?width=1600&format=jpeg&auto=webp",
                "We got the SODA!"))
        imageList.add(SlideModel("https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/articles/health_tools/12_powerhouse_vegetables_slideshow/intro_cream_of_crop.jpg"))

        val imageSlider = findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList)

        fun clcik0() {

            Toast.makeText(this, "", Toast.LENGTH_LONG).show()
        }
        //on Click for image slider
        imageSlider.setItemClickListener(object : ItemClickListener {
            override fun onItemSelected(position: Int) {
                // You can listen here
                when (position) {
                    0 -> {
                        clcik0()
                    }
                    1 -> { }
                    else -> { }
                }
            }
        })
    }//END: private fun ISinit()


    private fun init() {
        getData()
        recycler_view.layoutManager =
            GridLayoutManager(
                this,
                2
            ) //init Layout Manager : picks layout  // mContext = this ; this lets the adapter know that this current
        adapterCategory = AdapterCategory(this, mList) // activty is using the adapter for the recycle view

        recycler_view.adapter = adapterCategory //set the adapter to the recycler view

        /* button_logout_Main.setOnClickListener{
             var sharedPreferences = getSharedPreferences("my_login", Context.MODE_PRIVATE)
             var editor = sharedPreferences.edit()
             editor.clear() // clears the file
             startActivity(Intent(this,LaunchActivity::class.java))
         }*/
        logout()
    }

    private fun logout() {
        button_logout_Main.setOnClickListener {
            var sessionManager = SessionManager(this)
            sessionManager.logout()
            startActivity(Intent(this, LaunchActivity::class.java))
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
