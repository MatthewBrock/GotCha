package com.inc.gotcha.gotcha

data class ProfileData(var mediaList: List<MediaElement>)
data class MediaElement(var mediaType: String?, var mediaHandle: String?)