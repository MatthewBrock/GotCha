package com.inc.gotcha.gotcha.ui.landingpage

import android.view.View
import androidx.lifecycle.LiveData

interface ILandingPageViewModel {

    fun title(): LiveData<String>

    fun onNextButtonClicked(): View.OnClickListener
}