package com.listing.grocery.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.listing.grocery.data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1
)
abstract class ProductsDB: RoomDatabase() {

    abstract val dao: ProductDao
}