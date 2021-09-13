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
import com.example.kolomachinetest.api.repo.marvel.data.ApiResponse
import com.example.kolomachinetest.api.repo.marvel.data.Result
import com.example.kolomachinetest.databinding.FragmentListBaseBinding
import com.example.kolomachinetest.ui.adapter.PaginationCallback
import retrofit2.Call

abstract class ListBaseFragment : BaseFragment(), PaginationCallback {

    lateinit var mRecyclerView: RecyclerView
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mTextView: TextView
    private lateinit var mBaseViewModel: BaseViewModel
    private var _binding: FragmentListBaseBinding? = null
    private val binding get() = _binding!!

    abstract fun getApi(pos: Int): Call<ApiResponse>
    abstract fun onSuccess(results: ArrayList<Result>, showLoadingScreen: Boolean)
    abstract fun onFailure(message: String?, pos: Int)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBaseViewModel =
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
        mBaseViewModel.mApiResultLiveData.observe(viewLifecycleOwner, {
            mProgressBar.visibility = View.INVISIBLE
            mTextView.visibility = View.INVISIBLE
            onSuccess(it.list, it.showProgress)
        })
        mBaseViewModel.mErrorLiveModel.observe(viewLifecycleOwner, {
            if (it.string != null) {
                mProgressBar.visibility = View.INVISIBLE
                onFailure(it.string, it.position)
                if (it.position == 0) {
                    mTextView.visibility = View.VISIBLE
                } else {
                    onLoadMore(it.position)
                }
            }
        })
    }

    override fun onLoadMore(position: Int) {
        mTextView.visibility = View.INVISIBLE
        mBaseViewModel.fetchData(getApi(position), position)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}