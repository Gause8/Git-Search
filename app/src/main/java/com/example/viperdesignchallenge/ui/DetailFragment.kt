package com.example.viperdesignchallenge.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.viperdesignchallenge.R
import com.example.viperdesignchallenge.data.network.response.Item
import com.squareup.picasso.Picasso

class DetailFragment: Fragment()   {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container,false)
        val data = arguments?.getParcelable<Item>("data")?.let{

            val loginText = view.findViewById<TextView>(R.id.login)
            loginText.text = it.login

            val idText = view.findViewById<TextView>(R.id.id)
            idText.text = it.id.toString()

            val nodeIdText = view.findViewById<TextView>(R.id.node_id)
            nodeIdText.text = it.node_id

            //val avatarUrl = view.findViewById<TextView>(R.id.avatar_url)
            //avatarUrl.text = it.avatar_url
            Picasso.get().load(Uri.parse(it.avatar_url.toString())).into(view.findViewById<ImageView>(R.id.pic))

            val gavatarId = view.findViewById<TextView>(R.id.gavatar_id)
            gavatarId.text = it.gravatar_id

            val urlText = view.findViewById<TextView>(R.id.url)
            urlText.text = it.url

            val htmlUrl = view.findViewById<TextView>(R.id.html_url)
            htmlUrl.text = it.html_url

            htmlUrl.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW,Uri.parse(htmlUrl.text.toString()))
                startActivity(intent)
            }


        }

        return view
    }
    companion object{
        fun newInstance(item: Item): DetailFragment{
            val fragment = DetailFragment()
            val bundle = Bundle().apply {
                putParcelable("data", item)
            }
            fragment.arguments = bundle
            return fragment
        }
    }
}