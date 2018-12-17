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

    private var mediaList = ArrayList<String>()

    private var fieldViewModels = ArrayList<IProfileFieldViewModel>()

    private var recyclerViews = ArrayList<RecyclerView>()
    private var viewAdapters = ArrayList<RecyclerView.Adapter<*>>()
    private var viewManagers = ArrayList<RecyclerView.LayoutManager>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        mediaList.addAll("Email,Phone,Kik,Facebook,Twitter,Instagram,Youtube,LinkedIn".split(","))

        viewModel = ProfileViewModel(context?.getSharedPreferences(getString(R.string.profile_data), Context.MODE_PRIVATE))

        fieldViewModels.addAll(viewModel.getFieldVMs() as Collection<IProfileFieldViewModel>)

        val binding: ProfileFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)

        setUpRecyclerViews(binding)

        binding.viewmodel = viewModel

        binding.fieldViewModel0 = fieldViewModels[0]
        binding.fieldViewModel1 = fieldViewModels[1]
        binding.fieldViewModel2 = fieldViewModels[2]
        binding.fieldViewModel3 = fieldViewModels[3]
        binding.fieldViewModel4 = fieldViewModels[4]

        binding.setLifecycleOwner(this)
        return binding.root
    }

    fun setUpRecyclerViews(binding: ProfileFragmentBinding) {
        for(i in 0..4) {
            viewManagers.add(LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false))
            viewAdapters.add(MyAdapter(mediaList, fieldViewModels[i]))
        }

        recyclerViews.add(binding.root.findViewById<View>(R.id.media_inputs).findViewById<View>(R.id.media0)
                .findViewById<RecyclerView>(R.id.media_selector).apply {
                    setHasFixedSize(true)
                    layoutManager = viewManagers[0]
                    adapter = viewAdapters[0]
                })

        recyclerViews.add(binding.root.findViewById<View>(R.id.media_inputs).findViewById<View>(R.id.media1)
                .findViewById<RecyclerView>(R.id.media_selector).apply {
                    setHasFixedSize(true)
                    layoutManager = viewManagers[1]
                    adapter = viewAdapters[1]
                })

        recyclerViews.add(binding.root.findViewById<View>(R.id.media_inputs).findViewById<View>(R.id.media2)
                .findViewById<RecyclerView>(R.id.media_selector).apply {
                    setHasFixedSize(true)
                    layoutManager = viewManagers[2]
                    adapter = viewAdapters[2]
                })

        recyclerViews.add(binding.root.findViewById<View>(R.id.media_inputs).findViewById<View>(R.id.media3)
                .findViewById<RecyclerView>(R.id.media_selector).apply {
                    setHasFixedSize(true)
                    layoutManager = viewManagers[3]
                    adapter = viewAdapters[3]
                })

        recyclerViews.add(binding.root.findViewById<View>(R.id.media_inputs).findViewById<View>(R.id.media4)
                .findViewById<RecyclerView>(R.id.media_selector).apply {
                    setHasFixedSize(true)
                    layoutManager = viewManagers[4]
                    adapter = viewAdapters[4]
                })
    }

    class MyAdapter(private val mediaList: ArrayList<String>, private val vm: IProfileFieldViewModel) :
            RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): MyAdapter.MyViewHolder {
            val textView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.media_selection_element, parent, false) as TextView
            return MyViewHolder(textView)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.textView.text = mediaList[position]
            holder.textView.setOnClickListener { vm.onTypeSelected(mediaList[position]) }
        }

        override fun getItemCount() = mediaList.size
    }
}
