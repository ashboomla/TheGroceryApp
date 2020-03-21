package com.example.thegroceryapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thegroceryapp.R
import com.example.thegroceryapp.appData.Config
import com.example.thegroceryapp.helpers.DBHelper
import com.example.thegroceryapp.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_cart_adapter.view.*
import kotlin.math.round

class AdapterCart(var mContext: Context) :
    RecyclerView.Adapter<AdapterCart.MyViewHolder>() {
    private var mList =  ArrayList<Product>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterCart.MyViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.row_cart_adapter, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: AdapterCart.MyViewHolder, position: Int) {
        var product = mList.get(position)
        holder.bind(product,position)
    }

    fun setData(product:ArrayList<Product>){
        mList = product // this initializes the class fields of the adapters: initalize: saves the product into the mlist
        notifyDataSetChanged() //
    }

    fun removeItem(position: Int) {
        mList.removeAt(position)
        notifyDataSetChanged()
    }

    interface CartCustomInterface{
        fun onItemCLicked(position: Int, view : View)
    }

    fun setMyCustomInterface(CartCustom:CartCustomInterface)
    {
      //  listener = CartCustom
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(product:Product,position: Int)
        {

            var dbHelper : DBHelper = DBHelper(mContext)

            itemView.text_view_product_name_RCA.text = product.productName
            itemView.text_view_mrp_RCA.text = "$${String.format("%.2f",product.mrp.toString())}"
            itemView.text_view_price_RCA.text = "$${String.format("%.2f",product.price.toString())}"
            itemView.text_view_qtyReq_RCA.text = product.quantity.toString()

            var total : Double = (product.price * product.quantity)
            var totalmrp : Double = (product.mrp * product.quantity)
            var savings : Double = (totalmrp - total)

            itemView.text_view_total_RCA.text = "$${String.format("%.2f",total).toString()}"



           // String.format("%.2f",<your String>) // this gives you 2 decimal places

            Picasso //get image
                .get()
                .load(Config.IMAGE_URL + product.image)//the path
                .placeholder(R.drawable.image_place_holder)
                .error(R.drawable.image_place_holder)
                .into(itemView.image_view_RCA)


            var quan: Int = itemView.text_view_qtyReq_RCA.text.toString().toInt()

            itemView.button_sub.setOnClickListener {
                if (quan > 0) {
                    quan--
                    itemView.text_view_qtyReq_RCA.text = quan.toString()
                    dbHelper.updateCartCount(product,false) //update to db

                }
                if(quan==0){
                    dbHelper.deleteCart(product) //remove from db
                    removeItem(position) // remove from recyclerView
                }
            }

            itemView.button_add.setOnClickListener {
                quan++
                itemView.text_view_qtyReq_RCA.text = quan.toString()
                dbHelper.updateCartCount(product,true)
            }

            itemView.button_Remove_RCA.setOnClickListener{
             dbHelper.deleteCart(product) //remove from db
             removeItem(position) // remove from recyclerView
            }
        }
    }
}