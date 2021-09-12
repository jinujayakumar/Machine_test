package com.example.kolomachinetest.ui.home

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kolomachinetest.api.CallBack
import com.example.kolomachinetest.api.PaginationCallback
import com.example.kolomachinetest.api.RemoteApiBuilder
import com.example.kolomachinetest.data.ApiResponse
import com.example.kolomachinetest.data.Character
import com.example.kolomachinetest.databinding.FragmentHomeBinding
import android.view.*
import com.example.kolomachinetest.R


class HomeFragment : Fragment(), PaginationCallback {

    private val TAG = "HomeFragment"
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mRecyclerView: RecyclerView
    private var list: ArrayList<Character> = ArrayList()
    private val mCharacterListAdapter = CharacterListAdapter(list, this)
    private lateinit var mProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        mRecyclerView = binding.recyclerView
        mProgressBar = binding.progressBar
        mRecyclerView.layoutManager = GridLayoutManager(activity, 3)
        mRecyclerView.adapter = mCharacterListAdapter
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onLoadMore(0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onLoadMore(position: Int) {
        RemoteApiBuilder.getCharacterList(position, object : CallBack<ApiResponse> {
            override fun onSuccess(result: ApiResponse) {
                val results = result.data.results
                val showLoadingScreen = result.data.total != result.data.count + result.data.offset
                mCharacterListAdapter.setResult(results, showLoadingScreen)
                mProgressBar.visibility = View.INVISIBLE
            }

            override fun onFailure(message: String?) {
                mProgressBar.visibility = View.INVISIBLE
                mCharacterListAdapter.resetLastItem()
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                Log.e(TAG, "onFailure: $message")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.toolbar_items_character, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_search) {
            TODO()
            return true
        }
        return false
    }
}