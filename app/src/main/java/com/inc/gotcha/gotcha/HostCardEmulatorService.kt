package com.inc.gotcha.gotcha

import android.content.Intent
import android.nfc.cardemulation.HostApduService
import android.os.Bundle
import android.util.Log

class HostCardEmulatorService : HostApduService() {

    companion object {
        val TAG = "Host Card Emulator"
        var message: String = ""
    }

    override fun onDeactivated(reason: Int) {
        Log.d(TAG, "Deactivated: " + reason)
    }

    override fun processCommandApdu(commandApdu: ByteArray?, extras: Bundle?): ByteArray {
        return message.toByteArray()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        message = ""
        intent?.let {
            message = intent.getStringExtra("Data")
        }
        return super.onStartCommand(intent, flags, startId)
    }
}