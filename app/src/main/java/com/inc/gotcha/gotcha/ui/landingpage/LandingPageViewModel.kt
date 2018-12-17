package com.inc.gotcha.gotcha.ui.landingpage

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.inc.gotcha.gotcha.R

class LandingPageViewModel(private val nfcController: NfcController, private val hceController: HceController) : ViewModel(), ILandingPageViewModel {

    private val title = MutableLiveData<String>()
    private val hceMessage = MutableLiveData<String>()

    init {
        title.value = "Hey there"
        hceMessage.value = "No HCE message yet"
    }

    override fun title(): LiveData<String> {
        return title
    }

    override fun onNextButtonClicked(): View.OnClickListener {
        return Navigation.createNavigateOnClickListener(R.id.action_landingPageFragment_to_profileFragment, null)
    }

    override fun setNfcMessage() {
        nfcController.sendNdefMessage("Yolo swag bugahti boiyoi ")
    }

    override fun hceMessage(): LiveData<String> {
        return hceMessage
    }

    fun hceMessageInput(message: String) {
        hceMessage.value = message
    }

    override fun startHceScan() {
        hceController.startHceScan()
    }
}