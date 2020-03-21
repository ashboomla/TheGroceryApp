package com.example.thegroceryapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thegroceryapp.R
import com.example.thegroceryapp.models.Address

class AdapterSelectAddress(var asaContext: Context) :
    RecyclerView.Adapter<AdapterSelectAddress.MyViewHolder>() {
    private var asaList = ArrayList<Address>()

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

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(address: Address,position: Int){


        }

    }
}