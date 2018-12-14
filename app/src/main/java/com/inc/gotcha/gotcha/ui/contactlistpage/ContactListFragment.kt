package com.inc.gotcha.gotcha.ui.contactlistpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.inc.gotcha.gotcha.R
import com.inc.gotcha.gotcha.databinding.ContactListFragmentBinding

class ContactListFragment : Fragment() {

    companion object {
        fun newInstance() = ContactListFragment()
    }

    private lateinit var viewModel: ContactListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(ContactListViewModel::class.java)
        val binding: ContactListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        binding.viewmodel = viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }
}
