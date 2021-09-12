package com.example.kolomachinetest.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kolomachinetest.api.RemoteApiBuilder
import com.example.kolomachinetest.api.CallBack
import com.example.kolomachinetest.api.PaginationCallback
import com.example.kolomachinetest.data.ApiResponse
import com.example.kolomachinetest.data.Character
import com.example.kolomachinetest.databinding.FragmentHomeBinding
import java.text.FieldPosition

class HomeFragment : Fragment(), PaginationCallback {

    private val TAG = "HomeFragment"
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mRecyclerView: RecyclerView
    private var list: ArrayList<Character> = ArrayList()
    private val mCharacterListAdapter = CharacterListAdapter(list, this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        mRecyclerView = binding.recyclerView
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
                mCharacterListAdapter.setResult(result.data.results)
            }

            override fun onFailure(message: String?) {
                mCharacterListAdapter.resetLastItem()
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                Log.e(TAG, "onFailure: $message")
            }
        })
    }
}