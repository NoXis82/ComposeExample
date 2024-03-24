package com.noxis.lib_product.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("numberQr")
    val numberQr: String,

    @ColumnInfo("image")
    val image: String?,

    @ColumnInfo("category")
    val category: String?
)
