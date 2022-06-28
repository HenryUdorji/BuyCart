package com.hashconcepts.buycart

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber.*
import timber.log.Timber.Forest.plant


/**
 * @created 28/06/2022 - 1:52 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@HiltAndroidApp
class BuyCartApp: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            plant(DebugTree())
        }
    }
}