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
    private val list = ArrayList<Result>()
    private lateinit var mFilterAdapter: FilterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        mListType = ListType.TYPE_FILTER
        mFormat = arguments?.getString("format").toString()
        mFormatType = arguments?.getString("formatType").toString()
        mNoVariants = arguments?.getString("noVariants").toString()
        mDateDescriptor = arguments?.getString("dateDescriptor").toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFilterAdapter = FilterAdapter(list, this, true)
        mRecyclerView.adapter = mFilterAdapter
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

    override fun onSuccess(results: ArrayList<Result>, showLoadingScreen: Boolean) {
        mFilterAdapter.setResult(results, showLoadingScreen)
    }

    override fun onFailure(message: String?, pos: Int) {
        mFilterAdapter.resetLastItem()
    }

    override fun onBackPressed() {
        findNavController().navigate(R.id.action_filterResultFragment_to_navigation_dashboard)
    }
}