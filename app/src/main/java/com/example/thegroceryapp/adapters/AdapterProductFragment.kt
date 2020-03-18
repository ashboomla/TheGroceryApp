package com.example.thegroceryapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thegroceryapp.R
import com.example.thegroceryapp.activities.ProductDetailActivity
import com.example.thegroceryapp.appData.Config
import com.example.thegroceryapp.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_product_fragment_adapter.view.*

class AdapterProductFragment(var mContext: Context) :
    RecyclerView.Adapter<AdapterProductFragment.MyViewHolder>() {

    private var mList : ArrayList<Product> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
var view = LayoutInflater.from(mContext).inflate(R.layout.row_product_fragment_adapter,parent,false)
    return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    /**
     * Binds the data from the model class to the placeholder in onCreateHolder
     * uses the method from inner class
     * get position tells which element in the array and then binds the model class data to the row data variables
     */
    override fun onBindViewHolder(holder: AdapterProductFragment.MyViewHolder, position: Int) {
    var product = mList.get(position)
        holder.bind(product,position) //bind view happens in inner class
    }


    fun setData(list:ArrayList<Product>)
    {
        mList = list
        notifyDataSetChanged() //sends the msg to the adapter to recreate all the row and fill it with the updated data
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        fun bind(product:Product,position: Int)
        {
            itemView.text_view_name.text = product.productName
           // itemView.text_view_desc.text = product.description

            Picasso
                .get()
                .load(Config.IMAGE_URL+product.image)//the path
                .placeholder(R.drawable.image_place_holder)
                .error(R.drawable.image_place_holder)
                .into(itemView.image_view_prod)
                //order is very important

            itemView.setOnClickListener{
                var productDetailIntent = Intent(mContext,ProductDetailActivity::class.java)
                productDetailIntent.putExtra(Product.KEY_PRODUCT,product)

                mContext.startActivity(productDetailIntent)
            }
        }
    }
}