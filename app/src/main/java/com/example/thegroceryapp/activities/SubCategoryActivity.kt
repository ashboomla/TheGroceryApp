package com.example.thegroceryapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.thegroceryapp.R
import com.example.thegroceryapp.adapters.AdapterSubCategory
import com.example.thegroceryapp.appData.Endpoints
import com.example.thegroceryapp.models.Category
import com.example.thegroceryapp.models.SubCategoryResponse
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_sub_category.*

class SubCategoryActivity : AppCompatActivity() {

    lateinit var category: Category
    lateinit var adapterSubCategory: AdapterSubCategory // lateinit = late initizilation : has no value until you later initialize it

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_category)

        init()
    }

    private fun init() {
        category = intent.getSerializableExtra(Category.KEY_CATEGORY) as Category//passing the whole object


        adapterSubCategory = AdapterSubCategory(supportFragmentManager)

        Toast.makeText(this, category.catImage, Toast.LENGTH_SHORT).show()
        var resCatName =
            intent.extras?.getString("CATNAME") //pulling in the data with key from AdapterCategory
        text_view.text = resCatName

        getData(category.catId)


    }
    private fun getData(catId: Int) {//getting subcategory


        var resCatId =
            intent.extras?.getInt("CATID") //pulling in the data with key from AdapterCategory

        Toast.makeText(this, "caught catID: $resCatId", Toast.LENGTH_LONG).show()

        var requestQueue =
            Volley.newRequestQueue(this) //Request Queue keeps track of all your requests

        var stringRequest = StringRequest(
            Request.Method.GET, Endpoints.getSubCategory(catId),
            Response.Listener {
                Log.d("data", it.toString()) // store it in log //must put it.tostring

                var gson = GsonBuilder().create() //creates an instance of gson
                var subcategoryResponse = gson.fromJson(
                    it.toString(),
                    SubCategoryResponse::class.java
                )
                var mSubList = subcategoryResponse.data
                for (sub in mSubList) {
                    adapterSubCategory.addFragment(sub)
                }

                view_pager.adapter = adapterSubCategory
                tab_layout.setupWithViewPager(view_pager)

               // Toast.makeText(this, "" + subcategoryResponse.data.size, Toast.LENGTH_SHORT).show()

            },
            Response.ErrorListener {
                Log.e("data", it?.message)
            })

        requestQueue?.add(stringRequest) //adding request to another object


    }
}
