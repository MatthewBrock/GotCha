package com.inc.gotcha.gotcha.ui.contactlistpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.inc.gotcha.gotcha.R
import com.inc.gotcha.gotcha.databinding.ContactListFragmentBinding
import androidx.recyclerview.widget.RecyclerView

class ContactListFragment : Fragment() {

    companion object {
        fun newInstance() = ContactListFragment()
    }

    private lateinit var viewModel: ContactListViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var nameList = ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        nameList.addAll("Crimi,JR,MattyB,Brendino".split(","))

        viewModel = ViewModelProviders.of(this).get(ContactListViewModel::class.java)
        val binding: ContactListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.contact_list_fragment, container, false)
        binding.viewmodel = viewModel

        viewManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        //viewAdapter = MyAdapter(nameList, viewModel)

        binding.setLifecycleOwner(this)
        return binding.root
    }
}

//        class MyAdapter(private val nameList: ArrayList<String>, private val vm: IContactListViewModel) :
//                RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
//
//            // Provide a reference to the views for each data item
//            // Complex data items may need more than one view per item, and
//            // you provide access to all the views for a data item in a view holder.
//            // Each data item is just a string in this case that is shown in a TextView.
//            class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
//
//
//            // Create new views (invoked by the layout manager)
//            override fun onCreateViewHolder(parent: ViewGroup,
//                                            viewType: Int): MyAdapter.MyViewHolder {
//                // create a new view
//                val textView = LayoutInflater.from(parent.context)
//                        .inflate(R.layout.contact_list_fragment, parent, false) as TextView
//                // set the view's size, margins, paddings and layout parameters
//                return MyViewHolder(textView)
//            }
//
//            // Return the size of your dataset (invoked by the layout manager)
//            override fun getItemCount() = nameList.size
//        }
