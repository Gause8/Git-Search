package com.example.viperdesignchallenge.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.viperdesignchallenge.R
import com.example.viperdesignchallenge.data.network.response.Item
import com.example.viperdesignchallenge.data.network.response.UserResponse

class SearchAdapter(var dataset: List<Item>, val searchDelegate: SearchDelegate):
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(){


    public interface SearchDelegate{
        public fun clickOnItem(item: Item)
    }
    inner class SearchViewHolder(val searchItem: View): RecyclerView.ViewHolder(searchItem){
        private val nameUser: TextView = searchItem.findViewById(R.id.name)
        private val idRepo: TextView = searchItem.findViewById(R.id.id)

        fun onBind(dataItem: Item){
            searchItem.setOnClickListener { searchDelegate.clickOnItem(dataItem) }
            nameUser.text = dataItem.login
            idRepo.text = dataItem.repos_url
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        SearchViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout,
            parent,
            false))


    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.onBind(dataset[position])
    }
}