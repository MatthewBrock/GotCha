package com.inc.gotcha.gotcha.ui.profilepage

import androidx.lifecycle.MutableLiveData

interface IProfileViewModel {
    val buttonVisibility: MutableLiveData<Int>
    val showEdit: MutableLiveData<Int>
    val showName: MutableLiveData<Int>
    val name: MutableLiveData<String>

    fun getFieldVMs(): ArrayList<IProfileFieldViewModel>
    fun save(type: String?, mediaString: String?)
    fun addHandle()
    fun editName()
    fun saveName()
}