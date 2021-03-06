package com.example.moviecatalogue.ui.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

@Suppress("UNCHECKED_CAST")
object LiveDataTestUtil {
    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)

        val observer = object : Observer<T> {
            override fun onChanged(o: T) {
                data[0] = o
                liveData.removeObserver(this)
            }
        }

        liveData.observeForever(observer)
        return data[0] as T
    }
}