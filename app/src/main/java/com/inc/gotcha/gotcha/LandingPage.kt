package com.inc.gotcha.gotcha

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LandingPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_page_activity)
    }

    override fun onResume() {
        super.onResume()
        val hceServiceIntent = Intent(this.applicationContext, HostCardEmulatorService::class.java)
        hceServiceIntent.putExtra("Data", "yolo swag bughati")
        startService(hceServiceIntent)// a bit of a hack to send data to this service, binding to it is hard
    }
}