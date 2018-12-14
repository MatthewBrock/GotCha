package com.inc.gotcha.gotcha.ui.landingpage

import android.view.View
import androidx.lifecycle.LiveData

interface ILandingPageViewModel {

    fun title(): LiveData<String>

    fun nfcMessage(): LiveData<String>

    fun hceMessage(): LiveData<String>

    fun onNextButtonClicked(): View.OnClickListener

    fun setNfcMessage()

    fun startHceScan()
}