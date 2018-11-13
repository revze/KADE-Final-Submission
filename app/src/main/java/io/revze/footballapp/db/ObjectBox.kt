package io.revze.footballapp.db

import android.content.Context
import io.objectbox.BoxStore
import io.revze.footballapp.model.MyObjectBox

object ObjectBox {
    lateinit var boxStore: BoxStore
    private set

    fun build(context: Context) {
        boxStore = MyObjectBox.builder().androidContext(context).build()
    }
}