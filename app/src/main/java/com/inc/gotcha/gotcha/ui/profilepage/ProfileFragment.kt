package com.inc.gotcha.gotcha.ui.profilepage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inc.gotcha.gotcha.R
import com.inc.gotcha.gotcha.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    private lateinit var fieldViewModel1: ProfileFieldViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var mediaList = ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        mediaList.addAll("Email,Phone,Kik,Facebook,Twitter,Instagram,Youtube,LinkedIn".split(","))

        viewModel = ProfileViewModel(context?.getSharedPreferences(getString(R.string.profile_data), Context.MODE_PRIVATE))
        fieldViewModel1 = ProfileFieldViewModel{a, b -> viewModel.save(a, b)}
        val binding: ProfileFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        binding.viewmodel = viewModel
        binding.fieldViewModel1 = fieldViewModel1

        viewManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        viewAdapter = MyAdapter(mediaList, fieldViewModel1)

        recyclerView = binding.root.findViewById<View>(R.id.media_inputs).findViewById<RecyclerView>(R.id.media_selector).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        binding.setLifecycleOwner(this)
        return binding.root
    }

    class MyAdapter(private val myDataset: ArrayList<String>, private val vm: IProfileFieldViewModel) :
            RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder.
        // Each data item is just a string in this case that is shown in a TextView.
        class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)


        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): MyAdapter.MyViewHolder {
            // create a new view
            val textView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.media_selection_element, parent, false) as TextView
            // set the view's size, margins, paddings and layout parameters
            return MyViewHolder(textView)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.textView.text = myDataset[position]
            holder.textView.setOnClickListener { vm.onTypeSelected(myDataset[position]) }
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = myDataset.size
    }
}
