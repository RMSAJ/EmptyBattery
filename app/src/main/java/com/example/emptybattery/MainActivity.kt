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
 private var myBatteryState: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        Log.e("Tag", "main")
        myBatteryState = MyBatteryState()
    }
    override fun onStart() {
        super.onStart()
        registerReceiver(myBatteryState, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }
}

open class MyBatteryState : BroadcastReceiver() {
    private var binding: ActivityMainBinding? = null


    override fun onReceive(context: Context?, intent: Intent? ) {
        Log.e("Tag", "onReceive")
        val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
            context?.registerReceiver(null, ifilter)
        }
        val status: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
        val isCharging: Boolean = status == BatteryManager.BATTERY_STATUS_CHARGING
                || status == BatteryManager.BATTERY_STATUS_FULL
        cheChargeState(isCharging)
        val batteryPct: Float? = batteryStatus?.let { intent ->
            val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            level * 100 / scale.toFloat()

        }
        checkpercentage(batteryPct)
    }

    fun checkpercentage(prc: Float?){

        when{

          prc!! > 95.00   -> {
              Log.e("Tag", "$prc")
              binding?.imageViewBattaryLevel?.setImageResource(R.drawable.ic_baseline_battery_full_24)}
          prc < 30.00   ->
        }
    }

        fun cheChargeState(chargeState: Boolean){
            when(chargeState) {
                chargeState ->  binding?.imageViewBattaryLevel?.setImageResource(R.drawable.ic_baseline_battery_charging_full_24)
                else -> {binding?.imageViewBattaryLevel?.setImageResource(R.drawable.ic_baseline_battery_charging_full_24)}
            }
        }
    }






