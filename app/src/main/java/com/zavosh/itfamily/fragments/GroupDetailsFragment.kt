package com.zavosh.itfamily.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.zavosh.itfamily.R
import com.zavosh.itfamily.adapters.GroupDetailsAdapter
import com.zavosh.itfamily.retrofit.Server
import kotlinx.android.synthetic.main.fragment_group_details.view.*


class GroupDetailsFragment : Fragment() {
    private lateinit var fragmentView :View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.fragment_group_details, container, false)
        setup()
        return fragmentView
    }

    private fun setup() {

        fragmentView.menu.setOnClickListener {
            activity?.findViewById<DrawerLayout>(R.id.mDrawerLayout)!!.openDrawer(GravityCompat.END)
        }

        fragmentView.img_back.setOnClickListener {

            activity?.onBackPressed()
        }

        getGroup()
    }

    private fun getGroup() {
        var id = arguments?.getString("id")
        Server.getInstance(context).getGroup(fragmentView?.loader_group,id){
            fragmentView?.recyclerView_group?.layoutManager = LinearLayoutManager(activity)
            fragmentView?.recyclerView_group?.adapter = GroupDetailsAdapter(activity,it)
        }
    }

}
