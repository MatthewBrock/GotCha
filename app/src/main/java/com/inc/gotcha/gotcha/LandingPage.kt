package com.inc.gotcha.gotcha

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity


class LandingPage : AppCompatActivity() {

    companion object {
        const val PROFILE = "PROFILE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_page_activity)
        setSupportActionBar(findViewById(R.id.toolbar))
        setStatusBarGradiant()
    }

    override fun onResume() {
        super.onResume()
        val profileDataString = getSharedPreferences(getString(R.string.profile_data), Context.MODE_PRIVATE)?.getString(PROFILE, "{}")
        val hceServiceIntent = Intent(this.applicationContext, HostCardEmulatorService::class.java)
        hceServiceIntent.putExtra("Data", profileDataString)
        startService(hceServiceIntent)// a bit of a hack to send data to this service, binding to it is hard
    }

    fun setStatusBarGradiant() {
        val window = window
        val background = resources.getDrawable(R.drawable.gradient_background)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.status_bar_tint)
        //window.navigationBarColor = activity.resources.getColor(android.R.color.transparent)
        window.setBackgroundDrawable(background)
    }
}