package com.example.thegroceryapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thegroceryapp.R
import com.example.thegroceryapp.models.Address
import kotlinx.android.synthetic.main.row_select_address_adapter.view.*

class AdapterSelectAddress(var asaContext: Context, var asaList: ArrayList<Address>) :
    RecyclerView.Adapter<AdapterSelectAddress.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterSelectAddress.MyViewHolder {

        var view = LayoutInflater.from(asaContext)
            .inflate(R.layout.row_select_address_adapter, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return asaList.size
    }

    override fun onBindViewHolder(holder: AdapterSelectAddress.MyViewHolder, position: Int) {
        var address = asaList.get(position)
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

        }

    }
}