package com.hashconcepts.buycart.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hashconcepts.buycart.domain.model.UserProfile
import com.hashconcepts.buycart.domain.model.Product

/**
 * @created 12/07/2022 - 4:34 AM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Database(
    entities = [
        UserProfile::class,
        Product::class
    ],
    exportSchema = false,
    version = 2
)
@TypeConverters(BuyCartConverters::class)
abstract class BuyCartDatabase : RoomDatabase() {

    abstract fun getBuyCartDao(): BuyCartDao
}