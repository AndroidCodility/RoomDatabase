package com.codility.roomdatabase.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.os.AsyncTask
import com.codility.roomdatabase.User
import com.codility.roomdatabase.database.AppDatabase

class AddUserViewModel(application: Application) : AndroidViewModel(application) {
    private var appDatabase: AppDatabase? = null

    init {
        appDatabase = AppDatabase.getDatabase(getApplication())
    }

    fun addUser(user: User) {
        addAsyncTask(appDatabase!!).execute(user)
    }

    private class addAsyncTask constructor(private val appDatabase: AppDatabase) : AsyncTask<User, Void, Void>() {
        override fun doInBackground(vararg params: User): Void? {
            appDatabase.itemAndPersonModel().addUserModel(params[0])
            return null
        }
    }

    fun updateUser(user: User) {
        updateAsyncTask(appDatabase!!).execute(user)
    }

    private class updateAsyncTask constructor(val appDatabase: AppDatabase) : AsyncTask<User, Void, Void>() {
        override fun doInBackground(vararg user: User): Void? {
            //appDatabase.itemAndPersonModel().deleteUser(user[0])
            appDatabase.itemAndPersonModel().updateUser(user[0])
            return null
        }
    }
}