package com.example.viperdesignchallenge.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.viperdesignchallenge.R
import com.example.viperdesignchallenge.data.network.response.Item
import com.example.viperdesignchallenge.data.network.response.UserResponse
import com.example.viperdesignchallenge.util.toast

private const val ARG_PARAM1 = "param1"

class SearchFragment : Fragment(), SearchAdapter.SearchDelegate {

    private var param1: ArrayList<UserResponse>? = null
    private lateinit var activityHost: MainActivity
    private val searchAdapter: SearchAdapter = SearchAdapter(mutableListOf(), this)
    private lateinit var recyclerView: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity)
            activityHost = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelableArrayList(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerview)
        recyclerView.adapter = searchAdapter
    }

    fun updateSearchResults(userResponse: List<Item>) {
        Log.d("TAG_X", "updateSearchResults")
        searchAdapter.dataset = userResponse
        searchAdapter.notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        fun newInstance(dataset: ArrayList<UserResponse>) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_PARAM1, dataset)
                }
            }
    }

    override fun clickOnItem(item: Item) {
        activityHost.inflateDetailedView(item)

    }
}