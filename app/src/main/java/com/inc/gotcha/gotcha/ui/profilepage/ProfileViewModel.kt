package com.inc.gotcha.gotcha.ui.profilepage

import android.content.SharedPreferences
import android.content.res.Resources
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.inc.gotcha.gotcha.MediaElement
import com.inc.gotcha.gotcha.ProfileData
import com.inc.gotcha.gotcha.R

class ProfileViewModel(val sharedPref: SharedPreferences?, val resources: Resources) : ViewModel(), IProfileViewModel {
    val PROFILE = "PROFILE"

    private val fieldVMs = ArrayList<IProfileFieldViewModel>()

    init {
        val profileDataString = sharedPref?.getString(PROFILE,"{}")
        val data = Gson().fromJson(profileDataString, ProfileData::class.java)

        var mediaList = ArrayList<MediaElement>()

        if(data?.mediaList != null)
            mediaList.addAll(data.mediaList)

        for(i in 0..Math.min(5, mediaList.size) - 1) {
            fieldVMs.add(ProfileFieldViewModel(mediaList[i]?.mediaHandle, resources){a, b -> save(a,b)})
        }

        while(fieldVMs.size < 5) {
            fieldVMs.add(ProfileFieldViewModel(resources.getString(R.string.add_new_profile), resources){ a, b -> save(a,b)})
        }
    }

    override fun getFieldVMs(): ArrayList<IProfileFieldViewModel> = fieldVMs

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
