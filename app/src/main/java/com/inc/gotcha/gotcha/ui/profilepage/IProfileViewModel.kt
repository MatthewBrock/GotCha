package com.inc.gotcha.gotcha.ui.profilepage

import androidx.lifecycle.MutableLiveData

interface IProfileViewModel {
    val name: MutableLiveData<String>
    val email: MutableLiveData<String>
    val phone: MutableLiveData<String>
    val kik: MutableLiveData<String>
    val facebook: MutableLiveData<String>
    val twitter: MutableLiveData<String>
    val instagram: MutableLiveData<String>
    val youtube: MutableLiveData<String>
    val linkedin: MutableLiveData<String>

    fun save(type: String?, mediaString: String?)
}