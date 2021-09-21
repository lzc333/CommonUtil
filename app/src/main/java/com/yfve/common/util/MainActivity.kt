package com.yfve.common.util

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity","onCreate")
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