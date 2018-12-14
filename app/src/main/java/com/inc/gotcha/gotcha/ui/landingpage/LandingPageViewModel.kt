package com.inc.gotcha.gotcha.ui.landingpage

import android.nfc.NdefMessage
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.inc.gotcha.gotcha.R

class LandingPageViewModel(private val nfcController: NfcController) : ViewModel(), ILandingPageViewModel {

    private val title = MutableLiveData<String>()
    private val nfcMessage = MutableLiveData<String>()
    private val hceMessage = MutableLiveData<String>()

    init {
        title.value = "Hey there"
        nfcMessage.value = "No NFC Message yet"
        hceMessage.value = "No HCE message yet"
    }

    override fun title(): LiveData<String> {
        return title
    }

    override fun onNextButtonClicked(): View.OnClickListener {
        return Navigation.createNavigateOnClickListener(R.id.action_landingPageFragment_to_profileFragment, null)
    }

    override fun setNfcMessage() {
        nfcController.sendNdefMessage(nfcController.createNdefMessage())
    }

    override fun nfcMessage(): LiveData<String> {
        return nfcMessage
    }

    override fun hceMessage(): LiveData<String> {
        return hceMessage
    }

    fun nfcMessageInput(message: String) {
        nfcMessage.value = message
    }

    fun hceMessageInput(message: String) {
        hceMessage.value = message
    }

    override fun startHceScan() {

    }
}