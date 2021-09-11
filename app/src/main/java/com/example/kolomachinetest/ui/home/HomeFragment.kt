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
import com.example.kolomachinetest.api.ApiBuilder
import com.example.kolomachinetest.api.ApiCallback
import com.example.kolomachinetest.data.ApiResponse
import com.example.kolomachinetest.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val TAG = "HomeFragment"
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ApiBuilder.getCharacterList(object : ApiCallback.RestCallback<ApiResponse> {
            override fun onSuccess(result: ApiResponse?) {
                TODO("Not yet implemented")
            }

            override fun onFailure(message: String?) {
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                Log.e(TAG, "onFailure: $message")
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}