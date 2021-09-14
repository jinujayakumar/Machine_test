package com.example.kolomachinetest.ui.search

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.kolomachinetest.R
import com.example.kolomachinetest.ui.adapter.PaginationCallback
import com.example.kolomachinetest.api.AppDataManager
import com.example.kolomachinetest.api.repo.marvel.ListType
import com.example.kolomachinetest.api.repo.marvel.data.ApiResponse
import com.example.kolomachinetest.api.repo.marvel.data.Result
import com.example.kolomachinetest.ui.base.ListBaseFragment
import com.example.kolomachinetest.ui.filter.FilterAdapter
import retrofit2.Call

class SearchResultFragment : ListBaseFragment(), PaginationCallback {

    private lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        name = arguments?.getString("key").toString()
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

    override fun onBackPressed() {
        findNavController().navigate(R.id.action_searchResultFragment_to_navigation_home)
    }

    override fun getListType() = ListType.TYPE_SEARCH

}