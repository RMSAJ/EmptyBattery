package com.example.emptybattery

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.emptybattery.databinding.ActivityMainBinding



 var binding: ActivityMainBinding? = null


class MainActivity : AppCompatActivity() {
 lateinit var myBatteryState: MyBatteryState

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        myBatteryState = MyBatteryState()

        registerReceiver(myBatteryState, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myBatteryState)
    }

}








