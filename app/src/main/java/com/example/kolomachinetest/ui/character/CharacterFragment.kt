package com.example.kolomachinetest.ui.character

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.kolomachinetest.R
import com.example.kolomachinetest.api.PaginationCallback
import com.example.kolomachinetest.api.RemoteApiBuilder
import com.example.kolomachinetest.data.ApiResponse
import com.example.kolomachinetest.data.Result
import com.example.kolomachinetest.ui.base.ListBaseFragment
import retrofit2.Call


class CharacterFragment : ListBaseFragment(), PaginationCallback {

    private var list: ArrayList<Result> = ArrayList()
    private val mCharacterListAdapter = CharacterListAdapter(list, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView.adapter = mCharacterListAdapter
    }

    override fun onSuccess(results: ArrayList<Result>, showLoadingScreen: Boolean) {
        mCharacterListAdapter.setResult(results, showLoadingScreen)
    }

    override fun onFailure(message: String?, pos: Int) {
        if (pos != 0) {
            mCharacterListAdapter.resetLastItem()
        }
    }

    override fun getApi(pos: Int): Call<ApiResponse> {
        return RemoteApiBuilder.fetchCharacterList(pos)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.character_tool_bar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_search) {
            findNavController().navigate(R.id.action_navigation_home_to_search_fragment)
            return true
        }
        return false
    }
}