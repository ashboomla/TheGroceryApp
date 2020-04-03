package com.example.thegroceryapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thegroceryapp.R
import com.example.thegroceryapp.activities_checkout.AddAddressActivity
import com.example.thegroceryapp.activities_checkout.AddressDetailActivity
import com.example.thegroceryapp.activities_checkout.PaymentActivity
import com.example.thegroceryapp.models.Address
import kotlinx.android.synthetic.main.row_select_address_adapter.view.*

class AdapterSelectAddress(var asaContext: Context) : RecyclerView.Adapter<AdapterSelectAddress.MyViewHolder>() {

    var asaList: ArrayList<Address> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterSelectAddress.MyViewHolder {

        var view = LayoutInflater.from(asaContext)
            .inflate(R.layout.row_select_address_adapter, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return asaList.size
    }

    override fun onBindViewHolder(holder: AdapterSelectAddress.MyViewHolder, position: Int) {
        var address = asaList[position]
        holder.bind(address, position)
    }

    fun setData(list: ArrayList<Address>) //gives an instance of the array list (mlist) into list so you can use it from another class
    {
        asaList = list
        notifyDataSetChanged() //sends the msg to the adapter to recreate all the row and fill it with the updated data
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(address: Address, position: Int) {
            itemView.text_view_address_type_SAA.text = address.type
            itemView.text_view_name_SAA.text = address.name
            itemView.text_view_address_SAA.text = address.getAddress()
            itemView.text_view_zip_SAA.text = address.zip

            itemView.button_Modify_SAA.setOnClickListener{
                var intent = Intent(asaContext, AddressDetailActivity::class.java)
                intent.putExtra(Address.KEY_ADDRESS,address)
                asaContext.startActivity(intent)
            }

            itemView.button_Select_SAA.setOnClickListener {
                var intent = Intent(asaContext, PaymentActivity::class.java)
                intent.putExtra(Address.KEY_ADDRESS,address)
                asaContext.startActivity(intent)
            }

        }

    }
}