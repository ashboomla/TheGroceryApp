package com.example.thegroceryapp.helpers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.thegroceryapp.models.Product

class DBHelper(var mContext: Context) :
    SQLiteOpenHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION) {

    var db: SQLiteDatabase = writableDatabase

    companion object {
        private const val DATABASE_NAME = "CartDB"
        private const val DATABASE_VERSION = 5
        private const val TABLE_NAME = "cart"
        private const val COLUMNS_PRODUCT_NAME = "productName"
        private const val COLUMNS_PRICE = "price"
        private const val COLUMNS_QUANTITY = "quantity"
        private const val COLUMNS_ID = "_id"
        private const val COLUMNS_IMAGE = "image"
        private const val COLUMNS_MRP = "mrp"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var createTable =
            "create table $TABLE_NAME($COLUMNS_PRODUCT_NAME char(250),$COLUMNS_PRICE DOUBLE," +
                    "$COLUMNS_QUANTITY INTEGER,$COLUMNS_ID char(250),$COLUMNS_IMAGE char(250), $COLUMNS_MRP DOUBLE) "
        db?.execSQL(createTable)

        Log.d("db test", "onCreate")


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTable = "drop table $TABLE_NAME"
        db?.execSQL(dropTable)
        onCreate(db)
        Log.d("db test", "onUpgrade")
    }

    fun addCart(product: Product) {
        var contentValues = ContentValues()
        contentValues.put(COLUMNS_PRICE, product.price)
        contentValues.put(COLUMNS_PRODUCT_NAME, product.productName)
        contentValues.put(COLUMNS_QUANTITY, 1)
        contentValues.put(COLUMNS_ID, product._id)
        contentValues.put(COLUMNS_IMAGE, product.image)
        contentValues.put(COLUMNS_MRP,product.mrp)


        db.insert(TABLE_NAME, null, contentValues)
        Log.d("db test", "add sale")
    }

    fun readCart(): ArrayList<Product> {
        var mList: ArrayList<Product> = ArrayList()

        //selecting colums
        var columns = arrayOf(
            COLUMNS_PRICE,
            COLUMNS_QUANTITY,
            COLUMNS_PRODUCT_NAME,
            COLUMNS_ID,
            COLUMNS_MRP,
            COLUMNS_IMAGE
        )
        //kind of a special array: when we read the data from the db
        //selection: select * from table
        // select id, name , product, price from table
        //select * from table where id = 1
        //                      id = selection //1 = selection arguments
        // select id name price from table where id = 1


        //db.query(TABLE_NAME,columns, id, 1)
        var cursor = db.query(TABLE_NAME, columns, null, null, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            do {
                var price = cursor.getDouble(cursor.getColumnIndex(COLUMNS_PRICE))
                var id = cursor.getString(cursor.getColumnIndex(COLUMNS_ID))
                var quantity = cursor.getInt(cursor.getColumnIndex(COLUMNS_QUANTITY))
                var productName = cursor.getString(cursor.getColumnIndex(COLUMNS_PRODUCT_NAME))
                var mrp = cursor.getDouble(cursor.getColumnIndex(COLUMNS_MRP))
                var image = cursor.getString(cursor.getColumnIndex(COLUMNS_IMAGE))


                var product = Product(
                    image, id, productName, "", "", "", quantity, // cahnge this one to inventory
                    "", mrp, 0, price, false, "" // make sure this is same as uantity R
                )
                mList.add(product)

            } while (cursor.moveToNext())
        }
        cursor.close()
        return mList

    }

    // Updating Count
    fun updateCartCount(updateCart: Product, isIncrease : Boolean) {

        var query = "select * from $TABLE_NAME where $COLUMNS_ID=?"
        var cursor = db.rawQuery(query, arrayOf(updateCart._id.toString()))

        var quantity = 0
        if (cursor != null && cursor.moveToFirst()){
            quantity = cursor.getInt(cursor.getColumnIndex(COLUMNS_QUANTITY))
        }

        var contentValues = ContentValues() //what does content value do? is like a hashmap: key is the column name and value
        contentValues.put(COLUMNS_PRODUCT_NAME, updateCart.productName)
        contentValues.put(COLUMNS_PRICE, updateCart.price)

        if(isIncrease==true) {
            contentValues.put(COLUMNS_QUANTITY, quantity + 1)
        }
        else{            contentValues.put(COLUMNS_QUANTITY, quantity - 1)
        }

        var whereClause = "$COLUMNS_ID=?"
        var whereArgs = arrayOf(updateCart._id.toString())
        db.update(TABLE_NAME, contentValues, whereClause,whereArgs)


        Log.d("db test", "add sale")

        //hint:
    }

    fun getCartCount(product:Product) : Int{

        var query = "select * from $TABLE_NAME where $COLUMNS_ID=?"
        var cursor = db.rawQuery(query, arrayOf(product._id.toString()))

        var quantity = 0
        if (cursor != null && cursor.moveToFirst()){
            quantity = cursor.getInt(cursor.getColumnIndex(COLUMNS_QUANTITY))
        }
        return quantity
    }

    fun deleteCart(deleteCart: Product) {
        var whereClause = "$COLUMNS_ID=?"
        var whereArgs = arrayOf(deleteCart._id.toString())
        db.delete(TABLE_NAME, whereClause, whereArgs)
    }

    /** I really do not understand this: what is rawQuery??
     *  just like a normal select statement util you get to the "=?"
     *  the argument goes into a rawQuery as an array:
     *  this can have multiple conditions : each condition is an element in the array.
    */
     fun isItemInCart(product: Product):Boolean{
        var query = "select * from $TABLE_NAME where $COLUMNS_ID=?"
        var cursor = db.rawQuery(query, arrayOf(product._id.toString()))
        var count = cursor.count
        return count != 0
    }

    fun cartTotal(product:Product)
    {

    }
}
