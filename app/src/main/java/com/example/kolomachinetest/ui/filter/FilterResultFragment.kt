package com.example.kolomachinetest.ui.filter

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
import retrofit2.Call

class FilterResultFragment : ListBaseFragment(), PaginationCallback {

    private lateinit var mFormat: String
    private lateinit var mFormatType: String
    private lateinit var mNoVariants: String
    private lateinit var mDateDescriptor: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        mFormat = arguments?.getString("format").toString()
        mFormatType = arguments?.getString("formatType").toString()
        mNoVariants = arguments?.getString("noVariants").toString()
        mDateDescriptor = arguments?.getString("dateDescriptor").toString()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return false
    }

    override fun getApi(pos: Int): Call<ApiResponse> {
        return AppDataManager.searchComicsList(
            mFormat, mFormatType,
            mNoVariants, mDateDescriptor,
            pos)
    }

    override fun getListType() = ListType.TYPE_FILTER

    override fun onBackPressed() {
        findNavController().navigate(R.id.action_filterResultFragment_to_navigation_dashboard)
    }
}