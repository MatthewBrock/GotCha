package com.inc.gotcha.gotcha.ui.profilepage

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inc.gotcha.gotcha.ProfileDataOld
import com.google.gson.Gson
import com.inc.gotcha.gotcha.MediaElement
import com.inc.gotcha.gotcha.ProfileData

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
        val data = Gson().fromJson(profileDataString, ProfileDataOld::class.java)

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

    override fun save(mediaType: String?, mediaHandle: String?) {
        println("$mediaType $mediaHandle")

        val profileDataString = sharedPref?.getString(PROFILE,"{}")
        val data = Gson().fromJson(profileDataString, ProfileData::class.java)

        var tempList = ArrayList<MediaElement>()

        if(data?.mediaList != null)
            tempList.addAll(data.mediaList)

        tempList.add(MediaElement(mediaType?.toLowerCase(), mediaHandle))

        val dataToSave = Gson().toJson(ProfileData(tempList))

        val editor = sharedPref?.edit()
        editor?.putString(PROFILE, dataToSave)
        editor?.apply()
    }
}
