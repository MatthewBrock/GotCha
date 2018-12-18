package com.inc.gotcha.gotcha.ui.profilepage

import androidx.lifecycle.MutableLiveData

interface IProfileViewModel {
    val buttonVisibility: MutableLiveData<Int>

    fun getFieldVMs(): ArrayList<IProfileFieldViewModel>
    fun save(type: String?, mediaString: String?)
    fun addHandle()
}