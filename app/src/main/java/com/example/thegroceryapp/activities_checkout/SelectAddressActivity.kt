package com.example.thegroceryapp.activities_checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thegroceryapp.R
import com.example.thegroceryapp.adapters.AdapterSelectAddress
import com.example.thegroceryapp.appData.ConfigAddress
import com.example.thegroceryapp.models.Address
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_select_address.*

class SelectAddressActivity : AppCompatActivity() {

    lateinit var adapterSelectAddress: AdapterSelectAddress
    lateinit var databaseReference: DatabaseReference

    var addyList : ArrayList<Address> =ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_address)

        databaseReference = FirebaseDatabase.getInstance().getReference(ConfigAddress.FIREBASE_DATABASE_NAME)

        init()
        getData()
    }

    private fun init() {
        adapterSelectAddress = AdapterSelectAddress(this)
        recycler_view_select_address.layoutManager = LinearLayoutManager(this)
        recycler_view_select_address.adapter = adapterSelectAddress

        button_add_address_SAA.setOnClickListener {
            startActivity(Intent(this, AddAddressActivity::class.java))

        }
    }
    private fun getData() {
        databaseReference.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for(data in dataSnapshot.children){
                    var address: Address? = data.getValue(Address::class.java)
                    address?.key = data.key
                    addyList.add(address!!)
                }
                adapterSelectAddress.setData(addyList)
            }
        })
    }
}
