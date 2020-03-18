package com.example.thegroceryapp.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.thegroceryapp.R
import com.example.thegroceryapp.activities.SubCategoryActivity
import com.example.thegroceryapp.appData.Config
import com.example.thegroceryapp.models.Category
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_category_adapter.view.*

class AdapterCategory(var mContext: Context, var mList: ArrayList<Category>) :
    RecyclerView.Adapter<AdapterCategory.MyViewHolder>() {
    /*
   tells which view to use
     binds the row data to the view-activity: recycle view
    mContext = this (current activity) -->inflate--> tells to use this layout(row_category-adapter)
     this is the view for the plaeHolder --> row data

    * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.row_category_adapter, parent, false) // gives the view object
        return MyViewHolder(view)
    }

    //size
    override fun getItemCount(): Int {
        return mList.size
    }

    /**
     * Binds the data from the model class to the placeholder in onCreateHolder
     * uses the method from inner class
     * get position tells which element in the array and then binds the model class data to the row data variables
     */
    override fun onBindViewHolder(holder: AdapterCategory.MyViewHolder, position: Int) {
        var category = mList.get(position)
        holder.bind(category, position)
    }

    fun setData(list:ArrayList<Category>)
    {
        mList = list
        notifyDataSetChanged() //sends the msg to the adapter to recreate all the row and fill it with the updated data
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category, position:Int) {
            itemView.text_view_title.text = category.catName
          //  itemView.text_view_desc.text = category.catDescription  //row category adapter
            //this is where the bridge meets category comes from the activity // adapterCategory = AdapterCategory(this,mList)
            Log.d("good", category.catImage.toString()) // store it in log

            Picasso//order is very important
                .get()
                .load(Config.IMAGE_URL+category.catImage)//the path
                .placeholder(R.drawable.image_place_holder)
                .error(R.drawable.image_place_holder)
                .into(itemView.image_view)

            itemView.setOnClickListener{
                var subCategoryIntent = Intent(mContext, SubCategoryActivity::class.java)
             //   subCategoryIntent.putExtra("CATID", category.catId)
                subCategoryIntent.putExtra("CATNAME", category.catName)
                subCategoryIntent.putExtra(Category.KEY_CATEGORY, category)


                mContext.startActivity(subCategoryIntent) // must use mContext to access anything on the activity
            } // sets on click listener for each item in the view
        }
    }
}