package io.revze.footballapp

import android.app.Application
import io.revze.footballapp.db.ObjectBox

class App : Application(){
    override fun onCreate() {
        super.onCreate()

        ObjectBox.build(this)
    }
}