package com.fc9d.diet

import android.app.Application
import com.fc9d.diet.data.AppContainer
import com.fc9d.diet.data.AppDataContainer

class DietApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}