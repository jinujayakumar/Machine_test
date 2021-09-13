package com.example.kolomachinetest.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kolomachinetest.api.ApiCallback
import com.example.kolomachinetest.api.CallBack
import com.example.kolomachinetest.api.PaginationCallback
import com.example.kolomachinetest.data.ApiResponse
import com.example.kolomachinetest.data.Result
import com.example.kolomachinetest.databinding.FragmentListBaseBinding
import retrofit2.Call

abstract class ListBaseFragment : BaseFragment(), PaginationCallback, CallBack<ApiResponse> {

    lateinit var mRecyclerView: RecyclerView
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mTextView: TextView
    private lateinit var characterViewModel: BaseViewModel
    private var _binding: FragmentListBaseBinding? = null
    private val binding get() = _binding!!

    abstract fun getApi(pos: Int): Call<ApiResponse>
    abstract fun onSuccess(results: ArrayList<Result>, showLoadingScreen: Boolean)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        characterViewModel =
            ViewModelProvider(this).get(BaseViewModel::class.java)

        _binding = FragmentListBaseBinding.inflate(inflater, container, false)
        val root: View = binding.root
        mRecyclerView = binding.recyclerView
        mProgressBar = binding.progressBar
        mTextView = binding.errorView
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mTextView.setOnClickListener {
            onLoadMore(0)
        }
        mRecyclerView.layoutManager = GridLayoutManager(activity, 3)
        onLoadMore(0)
    }

    override fun onLoadMore(position: Int) {
        mTextView.visibility = View.INVISIBLE
        getApi(position).enqueue(ApiCallback(this, position))
    }

    final override fun onSuccess(result: ApiResponse) {
        val results = result.data.results
        val showLoadingScreen = result.data.total != result.data.count + result.data.offset
        mProgressBar.visibility = View.INVISIBLE
        mTextView.visibility = View.INVISIBLE
        onSuccess(results, showLoadingScreen)
    }

    override fun onFailure(message: String?, pos: Int) {
        mProgressBar.visibility = View.INVISIBLE
        if (pos == 0) {
            mTextView.visibility = View.VISIBLE
        } else {
            onLoadMore(pos)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}