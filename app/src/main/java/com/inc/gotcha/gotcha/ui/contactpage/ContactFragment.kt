package com.inc.gotcha.gotcha.ui.contactpage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.google.gson.Gson
import com.inc.gotcha.gotcha.MediaElement
import com.inc.gotcha.gotcha.ProfileData
import com.inc.gotcha.gotcha.R
import com.inc.gotcha.gotcha.databinding.ContactFragmentBinding
import com.inc.gotcha.gotcha.ui.landingpage.LandingPageFragmentDirections
import kotlinx.android.synthetic.main.contact_fragment.*

class ContactFragment : Fragment() {

    private var profile: ProfileData? = null

    companion object {
        fun newInstance() = ContactFragment()
        const val CONTACT = "CONTACT"
    }

    private lateinit var viewModel: ContactViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        val binding: ContactFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.contact_fragment, container, false)
        binding.viewmodel = viewModel
        binding.setLifecycleOwner(this)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val args: ContactFragmentArgs = ContactFragmentArgs.fromBundle(arguments)
        add_friend_button.setOnClickListener { saveProfile() }
        profile = Gson().fromJson(args.contact, ProfileData::class.java)
        if (context != null && profile != null) {
            contact_info_card_view_pager.adapter = MediaElementViewPagerAdapter(context!!, profile!!.mediaList.take(5))
        }
        setTouchListener(first_image, 0)
        setTouchListener(second_image, 1)
        setTouchListener(third_image, 2)
        setTouchListener(fourth_image, 3)
        setTouchListener(fifth_image, 4)
    }

    private fun setTouchListener(view: ImageView, position: Int) {
        if (profile?.mediaList!!.size > position) {
            profile?.mediaList!![position].let {
                setImage(profile!!.mediaList[position], view)
                view.setOnClickListener {
                    contact_info_card_view_pager.currentItem = position
                }
            }
        }
    }

    private fun setImage(mediaElement: MediaElement, imageView: ImageView) {
        when {
            mediaElement.mediaType.equals("email") -> {
                imageView.setImageResource(R.drawable.icn_email)
            }
            mediaElement.mediaType.equals("phone") -> {
                imageView.setImageResource(R.drawable.icn_phone)
            }
            mediaElement.mediaType.equals("kik") -> {
                imageView.setImageResource(R.drawable.icn_kik)
            }
            mediaElement.mediaType.equals("facebook") -> {
                imageView.setImageResource(R.drawable.icn_facebook)
            }
            mediaElement.mediaType.equals("twitter") -> {
                imageView.setImageResource(R.drawable.icn_twitter)
            }
            mediaElement.mediaType.equals("instagram") -> {
                imageView.setImageResource(R.drawable.icn_instagram)
            }
            mediaElement.mediaType.equals("youtube") -> {
                imageView.setImageResource(R.drawable.icn_youtube)
            }
            mediaElement.mediaType.equals("linkedin") -> {
                imageView.setImageResource(R.drawable.icn_linkedin)
            }
        }
    }

    private fun saveProfile() {
        val sharedPref = activity?.getSharedPreferences(getString(R.string.contact_data), Context.MODE_PRIVATE)
        val profileDataListString = sharedPref?.getString(CONTACT, "{}")
        var dataArray: Array<ProfileData>
        dataArray = if (profileDataListString.equals("{}")) {
            emptyArray()
        } else {
            Gson().fromJson<Array<ProfileData>>(profileDataListString, Array<ProfileData>::class.java)
        }
        val dataList: ArrayList<ProfileData> = dataArray.toCollection(ArrayList())
        dataList.add(profile!!)
        val dataListString = Gson().toJson(dataList.toArray())
        val editor = sharedPref?.edit()
        editor?.putString(CONTACT, dataListString)
        editor?.apply()

        val action = ContactFragmentDirections.actionContactFragmentToContactListFragment()
        NavHostFragment.findNavController(this).navigate(action)

    }
}
