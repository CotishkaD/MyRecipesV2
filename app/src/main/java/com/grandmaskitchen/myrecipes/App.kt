package com.grandmaskitchen.myrecipes

import android.app.Application
import com.grandmaskitchen.myrecipes.di.AppComponent

/**
 * Created by Pustovit V.V.
 * Date: 21.09.2024
 * Time: 11:09
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppComponent.init(this)
    }
}