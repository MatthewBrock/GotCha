package com.inc.gotcha.gotcha.ui.profilepage

import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData

interface IProfileFieldViewModel {
    val mediaType: MutableLiveData<String>
    val mediaHandle: MutableLiveData<String>
    val defaultText: MutableLiveData<String>

    val visible: MutableLiveData<Int>
    val showDefault: MutableLiveData<Int>
    val showList: MutableLiveData<Int>
    val showEditor: MutableLiveData<Int>

    val color: MutableLiveData<Int>
    val icon: MutableLiveData<Drawable>

    fun onAddProfileClicked()
    fun onSaveClicked()
    fun onTypeSelected(type: String)
    fun enterCreation()
    fun delete()
}