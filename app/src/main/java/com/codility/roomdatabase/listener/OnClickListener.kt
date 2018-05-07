package com.codility.roomdatabase.listener

import com.codility.roomdatabase.User

interface OnClickListener {

    fun onItemClick(u: User)

    fun onItemDelete(u:User)
}