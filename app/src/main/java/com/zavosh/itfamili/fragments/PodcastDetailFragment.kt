package com.zavosh.itfamili.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.jean.jcplayer.model.JcAudio
import com.zavosh.itfamili.R
import com.zavosh.itfamili.helper.PageManager
import com.zavosh.itfamili.retrofit.Server
import com.zavosh.itfamili.retrofit.mymodels.homeRequest.Podcast
import com.zavosh.itfamili.retrofit.mymodels.podcastlistrequest.PodcastListResult
import kotlinx.android.synthetic.main.fragment_pod_detail.view.*


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
        rootView = inflater.inflate(R.layout.fragment_pod_detail, container, false)
        setup()
        return rootView
    }

    private fun setup() {
        rootView.menu.setOnClickListener {
            activity?.findViewById<DrawerLayout>(R.id.mDrawerLayout)!!.openDrawer(GravityCompat.END)
        }
        rootView.img_back.setOnClickListener { activity?.onBackPressed() }


        try {
        val podcastDetail = bundle?.getParcelable<PodcastListResult>("podcast_detail")
        bindViews(podcastDetail)

        }catch (e:Exception){
            val podcastDetail = bundle?.getParcelable<Podcast>("podcast_home")
            bindViewsFromHome(podcastDetail)
        }


    }

    private fun bindViews(podcastDetail: PodcastListResult?) {

        rootView.tv_likes_count.text=(podcastDetail?.linkeCount?:"")+" نفر پسندیده اند "
        rootView.tv_comments.text= (podcastDetail?.commentCount?:"")+" نفر نظر داده اند "
        rootView.tv_title.text=podcastDetail?.title?:""
        rootView.tv_summery.text=podcastDetail?.summery?:""
        rootView.profile_avatar.setPicasso(podcastDetail?.image,activity)
        //rootView.link_address.text=podcastDetail?.linkAddress?:""
        rootView.link_address2.text=podcastDetail?.publishDate?:""
        //rootView.link_address3.text=podcastDetail?.linkAddress?:""

        if (podcastDetail?.isLike?.toBoolean()!!){
            rootView.iv_like?.setImageResource(R.mipmap.red_like)
        }else{
            rootView.iv_like?.setImageResource(R.drawable.like)
        }
        /*rootView.img_play.setOnClickListener {

            Log.i("log","link : "+podcastDetail?.linkAddress)

        }*/
        val jcAudios: ArrayList<JcAudio> = ArrayList()
        jcAudios.add(JcAudio.createFromURL("",podcastDetail?.linkAddress!!))

        rootView.jcplayer.initPlaylist(jcAudios)

        rootView.iv_like?.setOnClickListener {
            context?.let {
                Server.getInstance(it).like(podcastDetail.id , !podcastDetail.isLike.toBoolean())
                if (!podcastDetail.isLike.toBoolean()){
                    rootView.iv_like?.setImageResource(R.mipmap.red_like)
                }else{
                    rootView.iv_like?.setImageResource(R.drawable.like)
                }
            }

        }

        rootView.iv_comment.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("id",podcastDetail.id)
            PageManager.getInstance().goCommentFragment(bundle)
        }

    }

    private fun bindViewsFromHome(podcastDetail: Podcast?) {

        rootView.tv_likes_count.text=(podcastDetail?.linkeCount?:"")+" نفر پسندیده اند "
        rootView.tv_comments.text= (podcastDetail?.commentCount?:"")+" نفر نظر داده اند "
        rootView.tv_title.text=podcastDetail?.title?:""
        rootView.tv_summery.text=podcastDetail?.summery?:""
        rootView.profile_avatar.setPicasso(podcastDetail?.image,activity)
        //rootView.link_address.text=podcastDetail?.linkAddress?:""
        rootView.link_address2.text=podcastDetail?.publishDate?:""
        //rootView.link_address3.text=podcastDetail?.linkAddress?:""

        podcastDetail?.isLike?.let {

            if (podcastDetail?.isLike?.toBoolean()!!){
                rootView.iv_like?.setImageResource(R.mipmap.red_like)
            }else{
                rootView.iv_like?.setImageResource(R.drawable.like)
            }
        }


        /*rootView.img_play.setOnClickListener {

            Log.i("log","link : "+podcastDetail?.linkAddress)

        }*/
        val jcAudios: ArrayList<JcAudio> = ArrayList()
        jcAudios.add(JcAudio.createFromURL("",podcastDetail?.linkAddress!!))

        rootView.jcplayer.initPlaylist(jcAudios)

        rootView.iv_like?.setOnClickListener {
            context?.let {
                if (!podcastDetail?.isLike?.isNullOrEmpty()!!) {
                    Server.getInstance(it).like(podcastDetail.id, !podcastDetail.isLike.toBoolean())
                    if (!podcastDetail.isLike.toBoolean()) {
                        rootView.iv_like?.setImageResource(R.mipmap.red_like)
                    } else {
                        rootView.iv_like?.setImageResource(R.drawable.like)
                    }
                }
            }

        }

        rootView.iv_comment.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("id",podcastDetail.id)
            PageManager.getInstance().goCommentFragment(bundle)
        }

    }

    override fun onPause() {
        super.onPause()
        rootView.jcplayer.kill()

        //rootView.paus
    }
}