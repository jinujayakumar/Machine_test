package com.example.kolomachinetest.ui.character

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
import com.example.kolomachinetest.data.Result
import com.example.kolomachinetest.databinding.FragmentCharactersBinding
import android.view.*
import androidx.navigation.fragment.findNavController
import com.example.kolomachinetest.R


class CharacterFragment : Fragment(), PaginationCallback {

    private val TAG = "HomeFragment"
    private lateinit var characterViewModel: CharacterViewModel
    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!
    private lateinit var mRecyclerView: RecyclerView
    private var list: ArrayList<Result> = ArrayList()
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
        characterViewModel =
            ViewModelProvider(this).get(CharacterViewModel::class.java)

        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
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
        RemoteApiBuilder.fetchCharacterList(position, object : CallBack<ApiResponse> {
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