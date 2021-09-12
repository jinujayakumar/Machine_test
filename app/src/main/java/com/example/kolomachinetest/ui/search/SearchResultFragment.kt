package com.example.kolomachinetest.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kolomachinetest.R
import com.example.kolomachinetest.api.CallBack
import com.example.kolomachinetest.api.PaginationCallback
import com.example.kolomachinetest.api.RemoteApiBuilder
import com.example.kolomachinetest.data.ApiResponse
import com.example.kolomachinetest.data.Result
import com.example.kolomachinetest.ui.filter.FilterAdapter

class SearchResultFragment : Fragment(), PaginationCallback, CallBack<ApiResponse> {

    private val TAG = "SearchResultFragment"
    private lateinit var name: String
    private val list = ArrayList<Result>()
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mFilterAdapter: FilterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
        name = arguments?.getString("key").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView = view.findViewById(R.id.recyclerView)
        mRecyclerView.layoutManager = GridLayoutManager(activity, 3)
        mFilterAdapter = FilterAdapter(list, this)
        mRecyclerView.adapter = mFilterAdapter
        onLoadMore(0)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            val findNavController = findNavController()
            findNavController.navigate(R.id.action_searchResultFragment_to_navigation_home)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onLoadMore(position: Int) {
        RemoteApiBuilder.searchCharacterList(
            position, name,
            this
        )
    }

    override fun onSuccess(result: ApiResponse) {
        val results = result.data.results
        val showLoadingScreen = result.data.total != result.data.count + result.data.offset
        mFilterAdapter.setResult(results, showLoadingScreen)
    }

    override fun onFailure(result: String?) {
        mFilterAdapter.resetLastItem()
        Log.e(TAG, "onFailure: $result")
    }

}