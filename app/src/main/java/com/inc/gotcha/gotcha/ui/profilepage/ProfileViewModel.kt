package com.inc.gotcha.gotcha.ui.profilepage

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inc.gotcha.gotcha.ProfileData
import com.google.gson.Gson

class ProfileViewModel(val sharedPref: SharedPreferences?) : ViewModel(), IProfileViewModel {
    val PROFILE = "PROFILE"

    override val name = MutableLiveData<String>()
    override val email = MutableLiveData<String>()
    override val phone = MutableLiveData<String>()
    override val kik = MutableLiveData<String>()
    override val facebook = MutableLiveData<String>()
    override val twitter = MutableLiveData<String>()
    override val instagram = MutableLiveData<String>()
    override val youtube = MutableLiveData<String>()
    override val linkedin = MutableLiveData<String>()

    init {
        val profileDataString = sharedPref?.getString(PROFILE,"{}")
        val data = Gson().fromJson(profileDataString, ProfileData::class.java)

        name.value = data.name
        email.value = data.email
        phone.value = data.phone
        kik.value = data.kik
        facebook.value = data.facebook
        twitter.value = data.twitter
        instagram.value = data.instagram
        youtube.value = data.youtube
        linkedin.value = data.linkedIn
    }

    override fun save() {
        val profileData = ProfileData(name.value, email.value, phone.value, kik.value, facebook.value,
                twitter.value, instagram.value, youtube.value, linkedin.value)
        val profileDataString = Gson().toJson(profileData)

        val editor = sharedPref?.edit()
        editor?.putString(PROFILE, profileDataString)
        editor?.apply()
    }
}
