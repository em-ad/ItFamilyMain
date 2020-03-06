package com.zavosh.itfamily.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zavosh.itfamily.R
import com.zavosh.itfamily.retrofit.mymodels.podcastlistrequest.PodcastListResult
import kotlinx.android.synthetic.main.activity_pari_pod_details.view.*

class PodcastDetailFragment: Fragment() {


    private lateinit var rootView: View
    var bundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bundle = arguments

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.activity_pari_pod_details, container, false)
        setup()
        return rootView
    }

    private fun setup() {

        val podcastDetail = bundle?.getParcelable<PodcastListResult>("podcast_detail")

        bindViews(podcastDetail)

    }

    private fun bindViews(podcastDetail: PodcastListResult?) {

        rootView.tv_likes_count.text=podcastDetail?.linkeCount?:""
        rootView.tv_comments.text=podcastDetail?.commentCount?:""
        rootView.tv_title.text=podcastDetail?.title?:""
        rootView.tv_summery.text=podcastDetail?.summery?:""
        rootView.profile_avatar.setPicasso(podcastDetail?.image,activity)
        rootView.link_address.text=podcastDetail?.linkAddress?:""
        rootView.link_address2.text=podcastDetail?.linkAddress?:""
        rootView.link_address3.text=podcastDetail?.linkAddress?:""

    }
}