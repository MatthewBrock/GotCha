package com.inc.gotcha.gotcha.ui.profilepage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.inc.gotcha.gotcha.R
import com.inc.gotcha.gotcha.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ProfileViewModel(context?.getSharedPreferences(getString(R.string.profile_data), Context.MODE_PRIVATE))
        val binding: ProfileFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        binding.viewmodel = viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }
}
