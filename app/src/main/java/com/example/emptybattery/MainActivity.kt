package com.example.emptybattery

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.example.emptybattery.databinding.ActivityMainBinding



private var binding: ActivityMainBinding? = null


class MainActivity : AppCompatActivity() {
 lateinit var myBatteryState: MyBatteryState

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val myBatter = binding!!.imageViewBattaryLevel
        myBatteryState = MyBatteryState(myBatter)

        registerReceiver(myBatteryState, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myBatteryState)
    }

}








