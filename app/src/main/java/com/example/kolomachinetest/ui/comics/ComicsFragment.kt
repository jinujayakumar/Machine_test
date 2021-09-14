package com.example.kolomachinetest.ui.comics

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
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

class ComicsFragment : ListBaseFragment(), PaginationCallback {

    private var list: ArrayList<Result> = ArrayList()
    private val mComicsListAdapter = ComicsListAdapter(list, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun getListType() = ListType.TYPE_COMICS

    override fun getApi(pos: Int): Call<ApiResponse> {
        return AppDataManager.fetchComicsList(pos)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.comics_tool_bar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_filter) {
            findNavController().navigate(R.id.action_navigation_dashboard_to_filterFragment)
            return true
        }
        return false
    }

    override fun onBackPressed() {
        requireActivity().finish()
    }
}