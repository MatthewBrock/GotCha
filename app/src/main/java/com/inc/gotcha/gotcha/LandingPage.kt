package com.inc.gotcha.gotcha

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LandingPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_page_activity)
    }

    override fun onNewIntent(intent: Intent) {
        setIntent(intent)
    }
}