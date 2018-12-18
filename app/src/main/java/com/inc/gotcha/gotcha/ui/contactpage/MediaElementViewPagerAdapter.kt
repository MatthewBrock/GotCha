package com.inc.gotcha.gotcha.ui.contactpage

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import com.inc.gotcha.gotcha.MediaElement
import com.inc.gotcha.gotcha.ProfileData
import com.inc.gotcha.gotcha.R

class MediaElementViewPagerAdapter(private val context: Context, private val mediaElementList: List<MediaElement>, private val profileData: ProfileData) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val mediaElement = mediaElementList[position]
        val inflater = LayoutInflater.from(context)
        val viewGroup: ViewGroup = inflater.inflate(R.layout.contact_card, container, false) as ViewGroup
        val cardView = viewGroup.findViewById<CardView>(R.id.contact_card)
        val contentImageType = viewGroup.findViewById<ImageView>(R.id.contact_type_image)
        val titleText = viewGroup.findViewById<TextView>(R.id.title)
        val descriptionText = viewGroup.findViewById<TextView>(R.id.description)
        titleText.text = profileData.name
        descriptionText.text = mediaElement.mediaHandle
        setImage(mediaElement, contentImageType, cardView)
        container.addView(viewGroup)
        return viewGroup
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return mediaElementList.size
    }

    //email, phone, kik, facebook, twitter, instagram, youtube, linkedIn
    private fun setImage(mediaElement: MediaElement, imageView: ImageView, cardView: CardView) {
        when {
            mediaElement.mediaType.equals("email") -> {
                imageView.setImageResource(R.drawable.icn_email_square)
                cardView.setCardBackgroundColor(cardView.resources.getColor(R.color.email_color))
            }
            mediaElement.mediaType.equals("phone") -> {
                imageView.setImageResource(R.drawable.icn_phone_square)
                cardView.setCardBackgroundColor(cardView.resources.getColor(R.color.phone_color))
            }
            mediaElement.mediaType.equals("kik") -> {
                imageView.setImageResource(R.drawable.icn_kik_square)
                cardView.setCardBackgroundColor(cardView.resources.getColor(R.color.kik_color))
            }
            mediaElement.mediaType.equals("facebook") -> {
                imageView.setImageResource(R.drawable.icn_facebook_square)
                cardView.setCardBackgroundColor(cardView.resources.getColor(R.color.fb_color))
            }
            mediaElement.mediaType.equals("twitter") -> {
                imageView.setImageResource(R.drawable.icn_twitter_square)
                cardView.setCardBackgroundColor(cardView.resources.getColor(R.color.twitter_color))
            }
            mediaElement.mediaType.equals("instagram") -> {
                imageView.setImageResource(R.drawable.icn_instagram_square)
                cardView.setCardBackgroundColor(cardView.resources.getColor(R.color.insta_color))
            }
            mediaElement.mediaType.equals("youtube") -> {
                imageView.setImageResource(R.drawable.icn_youtube_square)
                cardView.setCardBackgroundColor(cardView.resources.getColor(R.color.youtube_color))
            }
            mediaElement.mediaType.equals("linkedin") -> {
                imageView.setImageResource(R.drawable.icn_linkedin_square)
                cardView.setCardBackgroundColor(cardView.resources.getColor(R.color.linkedin_color))
            }
        }
    }
}