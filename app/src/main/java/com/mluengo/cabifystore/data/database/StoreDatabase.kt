package com.mluengo.cabifystore.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mluengo.cabifystore.data.database.dao.ProductDao
import com.mluengo.cabifystore.data.database.model.ProductEntity

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class StoreDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
}

