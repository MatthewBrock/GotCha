package com.inc.gotcha.gotcha.ui.contactlistpage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.inc.gotcha.gotcha.ProfileData
import com.inc.gotcha.gotcha.R
import com.inc.gotcha.gotcha.databinding.ContactListFragmentBinding
import com.inc.gotcha.gotcha.ui.contactpage.ContactFragment
import com.inc.gotcha.gotcha.ui.landingpage.LandingPageFragmentDirections
import kotlinx.android.synthetic.main.contact_list_fragment.*

class ContactListFragment : Fragment() {

    companion object {
        fun newInstance() = ContactListFragment()
    }

    private lateinit var viewModel: ContactListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(ContactListViewModel::class.java)
        val binding: ContactListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.contact_list_fragment, container, false)
        binding.viewmodel = viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val sharedPref = activity?.getSharedPreferences(getString(R.string.contact_data), Context.MODE_PRIVATE)
        val profileDataListString = sharedPref?.getString(ContactFragment.CONTACT, "{}")
        var dataList: Array<ProfileData>
        dataList = if (profileDataListString.equals("{}")) {
            emptyArray()
        } else {
            Gson().fromJson<Array<ProfileData>>(profileDataListString, Array<ProfileData>::class.java)
        }
        val fragment = this
        contact_list_view.layoutManager = LinearLayoutManager(activity)
        contact_list_view.adapter = ContactRecyclerViewAdapter(dataList.toList(), object : AdapterCallbacks {
            override fun openContactPage(profileData: ProfileData) {
                val action = ContactListFragmentDirections.actionContactListFragmentToContactFragment()
                action.setContact(Gson().toJson(profileData))
                NavHostFragment.findNavController(fragment).navigate(action)
            }
        })
    }
}
