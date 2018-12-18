package com.inc.gotcha.gotcha.ui.profilepage

import android.content.SharedPreferences
import android.content.res.Resources
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.inc.gotcha.gotcha.MediaElement
import com.inc.gotcha.gotcha.ProfileData

class ProfileViewModel(val sharedPref: SharedPreferences?, val resources: Resources) : ViewModel(), IProfileViewModel {
    val PROFILE = "PROFILE"

    override val buttonVisibility = MutableLiveData<Int>()
    override val showEdit = MutableLiveData<Int>()
    override val showName = MutableLiveData<Int>()
    override val name = MutableLiveData<String>()

    private val fieldVMs = ArrayList<IProfileFieldViewModel>()

    private var nextVMIndex = 5

    init {
        showEdit.value = View.GONE
        showName.value = View.VISIBLE

        val profileDataString = sharedPref?.getString(PROFILE,"{}")
        val data = Gson().fromJson(profileDataString, ProfileData::class.java)

        var mediaList = ArrayList<MediaElement>()

        if(data?.mediaList != null)
            mediaList.addAll(data.mediaList)

        name.value = "Name Here"

        if(data.name != null) {
            name.value = data.name
        }

        for(i in 0..Math.min(5, mediaList.size) - 1) {
            fieldVMs.add(ProfileFieldViewModel(true, mediaList[i]?.mediaHandle, mediaList[i]?.mediaType,
                    resources, {a, b -> save(a,b)}, {a, b -> delete(a,b)}))
        }

        while(fieldVMs.size < 5) {
            fieldVMs.add(ProfileFieldViewModel(false, "", "", resources, { a, b -> save(a,b)}, { a, b -> delete(a,b)}))
        }

        nextVMIndex = mediaList.size
        buttonVisibility.value = if(mediaList.size < 5) View.VISIBLE else View.GONE
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

        val dataToSave = Gson().toJson(ProfileData(name.value, tempList))

        val editor = sharedPref?.edit()
        editor?.putString(PROFILE, dataToSave)
        editor?.apply()

        if(nextVMIndex < 5)
            buttonVisibility.value = View.VISIBLE
    }

    fun delete(mediaType: String?, mediaHandle: String?) {
        println("$mediaType $mediaHandle")

        val profileDataString = sharedPref?.getString(PROFILE,"{}")
        val data = Gson().fromJson(profileDataString, ProfileData::class.java)

        var tempList = ArrayList<MediaElement>()

        if(data?.mediaList != null)
            tempList.addAll(data.mediaList)

        for(i in 0..tempList.size-1) {
            if(tempList[i].mediaType.equals(mediaType?.toLowerCase()) && tempList[i].mediaHandle.equals(mediaHandle)) {
                tempList.removeAt(i)
                break
            }
        }

        val dataToSave = Gson().toJson(ProfileData(name.value, tempList))

        val editor = sharedPref?.edit()
        editor?.putString(PROFILE, dataToSave)
        editor?.apply()
    }

    override fun addHandle() {
        if(nextVMIndex >= 5)
            return

        fieldVMs[nextVMIndex].enterCreation()
        nextVMIndex++

        buttonVisibility.value = View.GONE
    }

    override fun editName() {
        showName.value = View.GONE
        showEdit.value = View.VISIBLE
        buttonVisibility.value = View.GONE
    }

    override fun saveName() {

        if (showEdit.value == View.GONE)
            return

        val profileDataString = sharedPref?.getString(PROFILE,"{}")
        val data = Gson().fromJson(profileDataString, ProfileData::class.java)

        var tempList = ArrayList<MediaElement>()

        if(data?.mediaList != null)
            tempList.addAll(data.mediaList)

        val dataToSave = Gson().toJson(ProfileData(name.value, tempList))

        val editor = sharedPref?.edit()
        editor?.putString(PROFILE, dataToSave)
        editor?.apply()

        showEdit.value = View.GONE
        showName.value = View.VISIBLE

        if(nextVMIndex < 5)
            buttonVisibility.value = View.VISIBLE
    }
}
