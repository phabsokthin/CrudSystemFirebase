package com.example.firebasecrud

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasecrud.Adapter.UserAdapter
import com.example.firebasecrud.Model.UserModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ListActivity : AppCompatActivity() {

    private lateinit var ds: ArrayList<UserModel>
    private lateinit var dfRef:DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val rvUser = findViewById<RecyclerView>(R.id.rvUser)
        rvUser.layoutManager = LinearLayoutManager(this)
        rvUser.setHasFixedSize(true)
        ds = arrayListOf<UserModel>()
        getDataFromDatabase()

    }

    private fun getDataFromDatabase(){
        val rvUesr = findViewById<RecyclerView>(R.id.rvUser)

        val txtloading = findViewById<TextView>(R.id.txtloadingdata)

        rvUesr.visibility = View.GONE
        txtloading.visibility = View.VISIBLE
        dfRef = FirebaseDatabase.getInstance().getReference("Shopping")
        dfRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                ds.clear()

                if (snapshot.exists()){

                    for (userSnap in snapshot.children){
                        val userData = userSnap.getValue(UserModel::class.java)
                        ds.add(userData!!)
                    }
                }
                val userAdapter = UserAdapter(ds)
                rvUesr.adapter = userAdapter
                rvUesr.visibility = View.VISIBLE
                txtloading.visibility = View.GONE
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}