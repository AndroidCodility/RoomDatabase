package com.codility.roomdatabase

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.codility.roomdatabase.listener.OnClickListener
import com.codility.roomdatabase.viewmodel.UserRecyclerViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), OnClickListener {
    private var userRecyclerViewModel: UserRecyclerViewModel? = null
    private var myAdapter: MyAdapter? = null

    override fun onItemClick(u: User) {
        val intent = Intent(this, AddActivity::class.java)
        intent.putExtra("userObject", u); // sending our object
        startActivity(intent)
    }

    override fun onItemDelete(u: User) {
        showAlertDelete(u)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { _ ->
            startActivity(Intent(this, AddActivity::class.java))
        }

        myAdapter = MyAdapter(ArrayList<User>())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = myAdapter
        myAdapter!!.setListener(this)

        userRecyclerViewModel = ViewModelProviders.of(this).get(UserRecyclerViewModel::class.java)
        userRecyclerViewModel!!.getItemAndPersonList().observe(this, Observer<List<User>> { t ->
            myAdapter!!.addItems(t as ArrayList<User>)
        })
    }

    private fun showAlertDelete(u: User) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Confirm Delete...")
        alertDialog.setMessage("Are you sure you want delete this?")
        alertDialog.setIcon(android.R.drawable.ic_delete)
        alertDialog.setPositiveButton("YES", DialogInterface.OnClickListener { dialog, which ->
            userRecyclerViewModel!!.deleteItem(u)
        })
        alertDialog.setNegativeButton("NO", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })
        alertDialog.show()
    }

    override fun onResume() {
        super.onResume()
        myAdapter!!.notifyDataSetChanged()
    }
}