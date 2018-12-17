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
//import android.support.v7.widget.LinearLayoutManager
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.LinearLayoutManager
import com.inc.gotcha.gotcha.R.id.contactListFragment
import com.inc.gotcha.gotcha.R.id.rv_animal_list
import kotlinx.android.synthetic.main.contact_list_fragment.*
import kotlinx.android.synthetic.main.contact_list_fragment.view.*


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

//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.contact_list_item)
//
//        // Creates a vertical Layout Manager
//        rv_animal_list.linearmanager = LinearLayoutManager(this)
//
//        // Access the RecyclerView Adapter and load the data into it
//        rv_animal_list.linearmanager = AnimalAdapter(animals, this)
//
//        val animals: ArrayList<String> = ArrayList()
//        // Adds animals to the empty animals ArrayList
//        fun addAnimals() {
//            animals.add("dog")
//            animals.add("cat")
//            animals.add("owl")
//            animals.add("cheetah")
//            animals.add("raccoon")
//            animals.add("bird")
//            animals.add("snake")
//            animals.add("lizard")
//            animals.add("hamster")
//            animals.add("bear")
//            animals.add("lion")
//        }
//        addAnimals()
    }
}
