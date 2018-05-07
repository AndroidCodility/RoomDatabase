package com.codility.roomdatabase

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.codility.roomdatabase.viewmodel.AddUserViewModel
import kotlinx.android.synthetic.main.add_view.*


class AddActivity : AppCompatActivity() {
    private var user: User? = null
    private var addUserViewModel: AddUserViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_view)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Add User"
        addUserViewModel = ViewModelProviders.of(this).get(AddUserViewModel::class.java)

        if (intent.getSerializableExtra("userObject") != null) {
            setUserModel(intent.getSerializableExtra("userObject") as User)
        }
    }

    private fun setUserModel(user: User?) {
        if (user == null) {
            return
        }
        this.user = user
        supportActionBar!!.title = "Update User"
        btAdd.text = "Update"
        editName.setText(user.name)
        editItem.setText(user.itemName)
    }

    fun addView(view: View) {
        if (editName.text.isEmpty() || editName.text.isNullOrEmpty()) {
            showToast("person name is empty..!!")
            return
        }
        if (editItem.text.isEmpty() || editItem.text.isNullOrEmpty()) {
            showToast("Item name is empty..!!")
            return
        }

        if (this.user == null) {
            addUserViewModel!!.addUser(User(editName.text.toString(), editItem.text.toString()))
            showToast("Successfully added..!!")
        } else {
            user!!.name = editName.text.toString()
            user!!.itemName = editItem.text.toString()
            addUserViewModel!!.updateUser(user!!)
            showToast("Updated successfully..!!")
        }
        finish()
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}