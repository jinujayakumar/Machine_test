package com.example.kolomachinetest.ui.search

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.kolomachinetest.R
import com.example.kolomachinetest.ui.adapter.PaginationCallback
import com.example.kolomachinetest.api.AppDataManager
import com.example.kolomachinetest.api.repo.marvel.data.ApiResponse
import com.example.kolomachinetest.api.repo.marvel.data.Result
import com.example.kolomachinetest.ui.base.ListBaseFragment
import com.example.kolomachinetest.ui.filter.FilterAdapter
import retrofit2.Call

class SearchResultFragment : ListBaseFragment(), PaginationCallback {

    private lateinit var name: String
    private val list = ArrayList<Result>()
    private lateinit var mFilterAdapter: FilterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        name = arguments?.getString("key").toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFilterAdapter = FilterAdapter(list, this, false)
        mRecyclerView.adapter = mFilterAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getApi(pos: Int): Call<ApiResponse> {
        return AppDataManager.searchCharacterList(
            pos, name)
    }

    override fun onSuccess(results: ArrayList<Result>, showLoadingScreen: Boolean) {
        mFilterAdapter.setResult(results, showLoadingScreen)
    }

    override fun onBackPressed() {
        findNavController().navigate(R.id.action_searchResultFragment_to_navigation_home)
    }

    override fun onFailure(message: String?, pos: Int) {
        mFilterAdapter.resetLastItem()
    }

}