package com.inc.gotcha.gotcha.ui.profilepage

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileFieldViewModel(val saveCallback: (String?, String?) -> Unit) : ViewModel(), IProfileFieldViewModel {
    override val mediaType = MutableLiveData<String>()
    override val mediaHandle = MutableLiveData<String>()
    override val showDefault = MutableLiveData<Int>()
    override val showList = MutableLiveData<Int>()
    override val showEditor = MutableLiveData<Int>()

    init {
        mediaHandle.value = ""

        showDefault.value = View.VISIBLE
        showList.value = View.GONE
        showEditor.value = View.GONE
    }

    override fun onAddProfileClicked() {
        showDefault.value = View.GONE
        showList.value = View.VISIBLE
        showEditor.value = View.GONE
    }

    override fun onTypeSelected(type: String) {
        showDefault.value = View.GONE
        showList.value = View.GONE
        showEditor.value = View.VISIBLE

        mediaType.value = type
    }

    override fun onSaveClicked() {
        saveCallback(mediaType.value, mediaHandle.value)
    }
}