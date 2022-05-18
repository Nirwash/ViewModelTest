package com.nirwashh.android.viewmodeltest

import android.app.Application
import com.nirwashh.android.viewmodeltest.viewmodel.Model
import com.nirwashh.android.viewmodeltest.viewmodel.ViewModel

class MyApplication: Application() {
    lateinit var viewModel: ViewModel
    override fun onCreate() {
        super.onCreate()
        viewModel = ViewModel(Model())
    }
}