package com.nirwashh.android.viewmodeltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nirwashh.android.viewmodeltest.databinding.ActivityMainBinding
import com.nirwashh.android.viewmodeltest.viewmodel.TextCallback
import com.nirwashh.android.viewmodeltest.viewmodel.TextObservable
import com.nirwashh.android.viewmodeltest.viewmodel.ViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    private lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        viewModel = (application as MyApplication).viewModel

        val observable = TextObservable()
        observable.observe(object : TextCallback {
            override fun updateText(str: String) = runOnUiThread {
                b.tvMain.text = str
            }
        })
        viewModel.init(observable)
    }

    override fun onResume() {
        super.onResume()
        viewModel.resumeCounting()
    }

    override fun onPause() {
        super.onPause()
        viewModel.pauseCounting()
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()

    }
}