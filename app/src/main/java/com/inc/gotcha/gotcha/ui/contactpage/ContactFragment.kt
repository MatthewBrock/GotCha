package com.inc.gotcha.gotcha.ui.contactpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.inc.gotcha.gotcha.R
import com.inc.gotcha.gotcha.databinding.ContactFragmentBinding

class ContactFragment : Fragment() {

    companion object {
        fun newInstance() = ContactFragment()
    }

    private lateinit var viewModel: ContactViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        val binding: ContactFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        binding.viewmodel = viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }
}
