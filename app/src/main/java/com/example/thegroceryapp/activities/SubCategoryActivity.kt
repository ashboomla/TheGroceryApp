package com.example.thegroceryapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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
import kotlinx.android.synthetic.main.app_bar.*

class SubCategoryActivity : AppCompatActivity() {

    lateinit var category: Category // because you will init after the subDetails come in from the AdapterCategory
    lateinit var adapterSubCategory: AdapterSubCategory // lateinit = late initizilation : has no value until you later initialize it

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_category)
        category = intent.getSerializableExtra(Category.KEY_CATEGORY) as Category//passing the whole object from adapterCategory inner class fun bind

        init()
        setupToolBar()
    }

    private fun setupToolBar()
    {
        var toolbar = myCustomToolbar //assigns the id for the toolbar with the variable toolbar
        toolbar.title ="${category.catName}"
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_cart -> {
                Toast.makeText(this, "go to cart", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, CartActivity::class.java))
            }

            R.id.menu_home -> {
                Toast.makeText(this, "go home", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,MainActivity::class.java))
            }
            else -> {
                super.onOptionsItemSelected(item)
            }

        }
        return true
    }

    private fun init() {
        adapterSubCategory = AdapterSubCategory(supportFragmentManager)

        Toast.makeText(this, category.catImage, Toast.LENGTH_SHORT).show()
      //  text_view.text = category.catName

        getData(category.catId)


    }
    private fun getData(catId: Int) {//getting subcategory
      //  Toast.makeText(this, "caught catID: $category.catId", Toast.LENGTH_LONG).show()

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

            },
            Response.ErrorListener {
                Log.e("data", it?.message)
            })

        requestQueue?.add(stringRequest) //adding request to another object


    }
}
