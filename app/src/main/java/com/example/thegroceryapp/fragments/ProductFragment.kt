package com.example.thegroceryapp.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import com.example.thegroceryapp.R
import com.example.thegroceryapp.adapters.AdapterProductFragment
import com.example.thegroceryapp.appData.Endpoints
import com.example.thegroceryapp.models.Product
import com.example.thegroceryapp.models.ProductResponse
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_product.*
import kotlinx.android.synthetic.main.fragment_product.view.*
import kotlinx.android.synthetic.main.row_product_fragment_adapter.view.*
private const val ARG_PARAM1 = "param1"

class ProductFragment : Fragment() {
    lateinit var adapterProduct: AdapterProductFragment
    var mList: ArrayList<Product> = ArrayList() //Create an arrayList of the Category DC //make sure its inside the class being outside will cause it to have errors.

    //pass the sub id to this fragment in a text box; display that
    //make
    private var subId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            subId = it.getInt(ARG_PARAM1)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_product, container, false)// Inflate the layout for this fragment

        //view.textBOX.text = param1.toString()
        init(view)
        return view
    }


    private fun init(view: View) {
        //var subId = param1!!.toInt()
        Log.d("subId1", subId.toString())

        getProductData(subId)

        adapterProduct = AdapterProductFragment(activity as Context) //to typecast activity to another type
        view.recycler_view_product.layoutManager = LinearLayoutManager(activity) //init Layout Manager : picks layout
         // mContext = this ; this lets the adapter know that this current
        // activty is using the adapter for the recycle view

        view.recycler_view_product.adapter = adapterProduct //set the adapter to the recycler view


    }

    private fun getProductData(subId: Int) {//getting product based on subId from subCategory

        var requestQueue =
            Volley.newRequestQueue(activity) //Request Queue keeps track of all your requests

        var stringRequest = StringRequest(
            Request.Method.GET, Endpoints.getProduct(subId),
            Response.Listener {
                Log.d("data", it.toString()) // store it in log //must put it.tostring

                var gson = GsonBuilder().create() //creates an instance of gson
                var productResponse = gson.fromJson(
                    it.toString(),
                    ProductResponse::class.java
                )
                //var product = productResponse.data
                mList = productResponse.data // you have the data
                // set up with recycler view
                //Log.d("image1", mList[0].prodImage.toString())
                adapterProduct.setData(mList)
            },
            Response.ErrorListener {
                Log.e("data", it?.message)
            })
        requestQueue?.add(stringRequest) //adding request to another object
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            ProductFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }

}
