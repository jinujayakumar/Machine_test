package com.example.kolomachinetest.ui.search

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kolomachinetest.R
import com.example.kolomachinetest.api.RemoteApiBuilder
import com.example.kolomachinetest.api.repo.local.Search

class SearchFragment : Fragment(), SearchAdapter.SearchCallBack {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mEditText: EditText
    private var list: List<Search> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView = view.findViewById(R.id.recyclerView)
        mEditText = view.findViewById(R.id.editTextSearch)
        RemoteApiBuilder.initDb(requireActivity().applicationContext)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        list = RemoteApiBuilder.searchList() ?: list
        mRecyclerView.adapter = SearchAdapter(list, this)
        mEditText.imeOptions = EditorInfo.IME_ACTION_SEARCH
        mEditText.requestFocus()
        mEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                search(mEditText.text.toString(), true)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            val findNavController = findNavController()
            findNavController.popBackStack()
            return true
        }
        return false
    }

    override fun onSearchSelected(key: String?) {
        search(key, false)
    }

    private fun search(key: String?, insert: Boolean) {
        if (key != null && key.isNotEmpty()) {
            val args = Bundle()
            args.putString("key", key)
            if (insert) {
                RemoteApiBuilder.insertSearch(key)
            }
            findNavController().navigate(R.id.action_searchFragment_to_searchResultFragment, args)
        } else {
            Toast.makeText(activity, "Please enter a query", Toast.LENGTH_SHORT).show()
        }
    }
}