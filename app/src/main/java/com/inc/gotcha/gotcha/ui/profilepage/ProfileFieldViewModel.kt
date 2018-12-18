package com.inc.gotcha.gotcha.ui.profilepage

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inc.gotcha.gotcha.R

class ProfileFieldViewModel(val hasContent: Boolean, val handle: String?, var type: String?, val resources: Resources,
                            val saveCallback: (String?, String?) -> Unit,
                            val deleteCallback: (String?, String?) -> Unit) : ViewModel(), IProfileFieldViewModel {
    override val mediaType = MutableLiveData<String>()
    override val mediaHandle = MutableLiveData<String>()
    override val defaultText = MutableLiveData<String>()
    override val visible = MutableLiveData<Int>()
    override val showDefault = MutableLiveData<Int>()
    override val showList = MutableLiveData<Int>()
    override val showEditor = MutableLiveData<Int>()
    override val color = MutableLiveData<Int>()
    override val icon = MutableLiveData<Drawable>()

    init {
        if(hasContent)
            mediaHandle.value = handle
        else
            mediaHandle.value = ""

        defaultText.value = handle

        mediaType.value = type

        visible.value = if(hasContent) View.VISIBLE else View.GONE
        showDefault.value = View.VISIBLE
        showList.value = View.GONE
        showEditor.value = View.GONE

        setColorAndIcon()
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

        //will be fixed with enums
        mediaType.value = type.toLowerCase()
    }

    override fun onSaveClicked() {
        saveCallback(mediaType.value, mediaHandle.value)

        defaultText.value = mediaHandle.value

        showDefault.value = View.VISIBLE
        showList.value = View.GONE
        showEditor.value = View.GONE

        setColorAndIcon()
    }

    override fun enterCreation() {
        visible.value = View.VISIBLE

        showDefault.value = View.GONE
        showList.value = View.VISIBLE
        showEditor.value = View.GONE
    }

    override fun delete() {
        deleteCallback(mediaType.value, mediaHandle.value)
        visible.value = View.GONE
    }

    fun setColorAndIcon() {
        if(mediaType.value.equals("email")) {
            icon.value = resources.getDrawable(R.drawable.img_email)
            color.value = resources.getColor(R.color.email_color)
        } else if(mediaType.value.equals("phone")) {
            icon.value = resources.getDrawable(R.drawable.img_phone)
            color.value = resources.getColor(R.color.phone_color)
        } else if(mediaType.value.equals("kik")) {
            icon.value = resources.getDrawable(R.drawable.img_kik)
            color.value = resources.getColor(R.color.kik_color)
        } else if(mediaType.value.equals("facebook")) {
            icon.value = resources.getDrawable(R.drawable.img_facebook)
            color.value = resources.getColor(R.color.fb_color)
        } else if(mediaType.value.equals("twitter")) {
            icon.value = resources.getDrawable(R.drawable.img_twitter)
            color.value = resources.getColor(R.color.twitter_color)
        } else if(mediaType.value.equals("instagram")) {
            icon.value = resources.getDrawable(R.drawable.img_instagram)
            color.value = resources.getColor(R.color.insta_color)
        } else if(mediaType.value.equals("youtube")) {
            icon.value = resources.getDrawable(R.drawable.img_youtube)
            color.value = resources.getColor(R.color.youtube_color)
        } else if(mediaType.value.equals("linkedin")) {
            icon.value = resources.getDrawable(R.drawable.img_linkedin)
            color.value = resources.getColor(R.color.linkedin_color)
        } else {
            icon.value = null
            color.value = resources.getColor(R.color.transparent_color)
        }
    }
}