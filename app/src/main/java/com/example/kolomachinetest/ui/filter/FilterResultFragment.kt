package com.example.kolomachinetest.ui.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.kolomachinetest.R
import com.example.kolomachinetest.api.PaginationCallback

class FilterResultFragment : Fragment(), PaginationCallback {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter_result, container, false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            val findNavController = findNavController()
            findNavController.popBackStack()
            return true
        }
        return false
    }

    override fun onLoadMore(position: Int) {
        TODO("Not yet implemented")
    }

}