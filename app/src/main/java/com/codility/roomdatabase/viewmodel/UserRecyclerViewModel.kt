package com.codility.roomdatabase.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.codility.roomdatabase.User
import com.codility.roomdatabase.database.AppDatabase

class UserRecyclerViewModel(application: Application) : AndroidViewModel(application) {

    private var itemAndPersonList: LiveData<List<User>>? = null
    private var appDatabase: AppDatabase? = null

    init {
        appDatabase = AppDatabase.getDatabase(getApplication())
        itemAndPersonList = appDatabase!!.itemAndPersonModel().getAllUserItems()
    }

    fun getItemAndPersonList(): LiveData<List<User>> {
        return itemAndPersonList!!
    }

    fun deleteItem(user: User) {
        deleteAsyncTask(appDatabase!!).execute(user)
    }

    private class deleteAsyncTask internal constructor(private val db: AppDatabase) : AsyncTask<User, Void, Void>() {
        override fun doInBackground(vararg params: User): Void? {
            db.itemAndPersonModel().deleteUser(params[0])
            return null
        }
    }
}