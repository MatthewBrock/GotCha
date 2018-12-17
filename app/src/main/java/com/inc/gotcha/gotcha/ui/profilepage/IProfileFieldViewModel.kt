package com.inc.gotcha.gotcha.ui.profilepage

import androidx.lifecycle.MutableLiveData

interface IProfileFieldViewModel {
    val mediaType: MutableLiveData<String>
    val mediaHandle: MutableLiveData<String>
    val defaultText: MutableLiveData<String>

    val showDefault: MutableLiveData<Int>
    val showList: MutableLiveData<Int>
    val showEditor: MutableLiveData<Int>

    fun onAddProfileClicked()
    fun onSaveClicked()
    fun onTypeSelected(type: String)
}