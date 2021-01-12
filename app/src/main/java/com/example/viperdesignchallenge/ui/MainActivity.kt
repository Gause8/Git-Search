package com.example.viperdesignchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.viperdesignchallenge.R
import com.example.viperdesignchallenge.data.network.response.Item
import com.example.viperdesignchallenge.data.network.response.UserResponse
import com.example.viperdesignchallenge.databinding.ActivityMainBinding
import com.example.viperdesignchallenge.util.toast

class MainActivity : AppCompatActivity(), MainListener {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels<MainActivityViewModel>()

    private val searchFragment: SearchFragment = SearchFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel.mainListener = this
        binding.viewmodel = viewModel

        loadSearchFragment()
    }

    private fun loadSearchFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame_container, searchFragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as SearchView
        binding.viewmodel?.onSearch("Facebook")
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                p0?.let {
                    binding.viewmodel?.onSearch(it)
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onStarted() {
        toast("search started")
    }

    override fun onSuccess(response: List<Item>) {

        searchFragment.updateSearchResults(response)

    }

    override fun onFailure(message: String) {
        toast("search failed")
    }

    override fun inflateSearchFragment(dataset: List<UserResponse>) {
        supportFragmentManager.beginTransaction().replace(
            R.id.frame_container,
            SearchFragment.newInstance(dataset as ArrayList<UserResponse>)
        )
    }

    override fun inflateDetailedView(dataItem: Item) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container,
        DetailFragment.newInstance(dataItem)).addToBackStack(null).commit()


    }
}