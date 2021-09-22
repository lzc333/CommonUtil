package com.yfve.common.util

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.yfve.common.util.databinding.ActivityMainBinding
import kotlinx.coroutines.*


import androidx.lifecycle.ViewModelProviders


class MainActivity : AppCompatActivity() {
    private var viewModel :TestViewModel?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity","onCreate")
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding= DataBindingUtil
            .setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(TestViewModel::class.java)
        binding.lifecycleOwner = this
        binding.testVm = viewModel


        binding.testBt.clickWithTrigger(300){
            Log.d("MainActivity","click testBt")
            viewModel?.isVisible?.value = false
        }
        binding.test2Bt.setOnClickListener {
            Log.d("MainActivity","click test2Bt")
            viewModel?.isVisible2?.value = false
        }

        binding.recoveryBt.setOnClickListener {
            Log.d("MainActivity","click recovery")
            viewModel?.isVisible?.value = true
            viewModel?.isVisible2?.value = true
        }

        exampleSharedPreference()
    }

    private fun exampleSharedPreference(){
        val fileName = "test"
        SharedPreferenceUtils.INSTANCE.initOnce(this,fileName)
        SharedPreferenceUtils.INSTANCE.find(fileName)?.put("key1",1)
        val value = SharedPreferenceUtils.INSTANCE.find(fileName)?.get("key1",0)
        Log.d("MainActivity","exampleSharedPreference value = $value")
    }

}