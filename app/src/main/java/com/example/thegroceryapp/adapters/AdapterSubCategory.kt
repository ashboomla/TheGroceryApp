package com.example.thegroceryapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.thegroceryapp.fragments.ProductFragment
import com.example.thegroceryapp.models.SubCategory

//adapterFragment
class AdapterSubCategory(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var mFragmentList = ArrayList<Fragment>()
    private var mTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return mFragmentList.get(position)
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitleList.get(position)

    }

    // custom method to add the fragments
    //call it from the activity
    //pass the subCategory object
    fun addFragment(subCategory:SubCategory) //object contians info of the SUBCATEGORY MODEL
    {
        mFragmentList.add(ProductFragment.newInstance(subCategory.subId)) // new instace = pass sub id to catgory
        mTitleList.add(subCategory.subName)
    }
}