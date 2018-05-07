package com.codility.roomdatabase

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

/**
 * Created by Govind on 05/04/2018.
 */

@Entity
class User(var name: String) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    lateinit var personName: String
    lateinit var itemName: String

    constructor(name: String, item: String) : this(name) {
        this.personName = name
        this.itemName = item
    }
}