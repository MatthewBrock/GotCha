package com.inc.gotcha.gotcha.ui.landingpage

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.inc.gotcha.gotcha.R

class LandingPageViewModel : ViewModel(), ILandingPageViewModel {

    private val title = MutableLiveData<String>()

    init {
        title.value = "Hey there"
    }

    override fun title(): LiveData<String> {
        return title
    }

    override fun onNextButtonClicked(): View.OnClickListener {
        return Navigation.createNavigateOnClickListener(R.id.action_landingPageFragment_to_profileFragment, null)
    }
}
