package com.inc.gotcha.gotcha.ui.landingpage

import android.app.Activity
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.inc.gotcha.gotcha.R
import com.inc.gotcha.gotcha.databinding.LandingPageFragmentBinding

class LandingPageFragment : Fragment() {

    companion object {
        fun newInstance() = LandingPageFragment()
    }

    private lateinit var viewModel: LandingPageViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        viewModel = ViewModelProviders.of(this).get(LandingPageViewModel::class.java)
        val binding: LandingPageFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.landing_page_fragment, container, false)
        binding.viewmodel = viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }
}

