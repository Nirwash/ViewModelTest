package com.nirwashh.android.viewmodeltest.data

interface DataSource {

    fun saveInt(key: String, value: Int)

    fun getInt(key: String) : Int
}