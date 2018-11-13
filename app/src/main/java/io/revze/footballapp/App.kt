package io.revze.footballapp

import android.app.Application
import io.objectbox.BoxStore
import io.revze.footballapp.db.ObjectBox
import io.revze.footballapp.model.MyObjectBox

class App : Application(){
    override fun onCreate() {
        super.onCreate()

        ObjectBox.build(this)
    }
}