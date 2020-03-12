package com.example.thegroceryapp.appData


class Endpoints {

    companion object {

        private const val URL_CATEGORY = "category/"
        private const val URL_SUB_CATEGORY = "subcategory/"
        private const val URL_PRODUCT = "products/"

        fun getCategory():String{return Config.BASE_URL + URL_CATEGORY}
        fun getSubCategory(catID:Int):String{return Config.BASE_URL + URL_SUB_CATEGORY +catID}
        fun getProduct(subID:Int):String{return Config.BASE_URL + URL_PRODUCT + subID}

    }
}