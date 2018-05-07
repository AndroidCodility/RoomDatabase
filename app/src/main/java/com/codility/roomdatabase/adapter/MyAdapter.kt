package com.codility.roomdatabase

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.codility.roomdatabase.listener.OnClickListener

/**
 * Created by Govind on 05/04/2018.
 */
class MyAdapter(private var userList: ArrayList<User>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    private var onClickListener: OnClickListener? = null

    fun setListener(listener: OnClickListener) {
        this.onClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.bindItems(user)

        holder.itemView.setOnClickListener(View.OnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onItemClick(user)
            }
        })

        holder.itemView.findViewById<ImageButton>(R.id.ibDelete).setOnClickListener(View.OnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onItemDelete(user)
            }
        })
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(user: User) {
            val tvName = itemView.findViewById<TextView>(R.id.tvName)
            val tvAddress = itemView.findViewById<TextView>(R.id.tvAddress)
            tvName.text = user.name
            tvAddress.text = user.itemName
        }
    }

    fun addItems(users: ArrayList<User>) {
        this.userList = users
        notifyDataSetChanged()
    }
}