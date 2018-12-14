package com.inc.gotcha.gotcha.ui.landingpage

import android.nfc.NdefMessage

interface NfcController {

    fun sendNdefMessage(message: NdefMessage)

    fun createNdefMessage(): NdefMessage
}