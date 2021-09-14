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
import com.example.kolomachinetest.api.repo.marvel.ErrorType
import com.example.kolomachinetest.api.repo.marvel.ListType
import com.example.kolomachinetest.api.repo.marvel.data.ApiResponse
import com.example.kolomachinetest.api.repo.marvel.data.Result
import com.example.kolomachinetest.databinding.FragmentListBaseBinding
import com.example.kolomachinetest.ui.adapter.PaginationAdapter
import com.example.kolomachinetest.ui.adapter.PaginationCallback
import com.example.kolomachinetest.ui.character.CharacterListAdapter
import com.example.kolomachinetest.ui.comics.ComicsListAdapter
import com.example.kolomachinetest.ui.filter.FilterAdapter
import retrofit2.Call

abstract class ListBaseFragment : BaseFragment(), PaginationCallback {

    lateinit var mRecyclerView: RecyclerView
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mTextView: TextView
    private lateinit var mBaseViewModel: BaseViewModel
    private var _binding: FragmentListBaseBinding? = null
    private val binding get() = _binding!!
    private var mAdapter: PaginationAdapter<*, Result>? = null
    private var mLists: ArrayList<Result> = arrayListOf<Result>()

    abstract fun getApi(pos: Int): Call<ApiResponse>
    abstract fun getListType(): Int

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
        mAdapter = getListAdapter()
        mRecyclerView.adapter = mAdapter
        onLoadMore(0)
        mBaseViewModel.mApiResultLiveData.observe(viewLifecycleOwner, {
            mProgressBar.visibility = View.INVISIBLE
            mTextView.visibility = View.INVISIBLE
            onSuccess(it.mList, it.mShowProgress)
        })
        mBaseViewModel.mErrorLiveModel.observe(viewLifecycleOwner, {
            if (it.message != null) {
                mProgressBar.visibility = View.INVISIBLE
                onFailure()
                if (it.errorType == ErrorType.TYPE_UNKNOWN) {
                    if (it.position == 0) {
                        mTextView.visibility = View.VISIBLE
                    } else {
                        onLoadMore(it.position)
                    }
                } else {
                    mTextView.visibility = View.VISIBLE
                    mTextView.text = it.message
                    mTextView.setOnClickListener(null)
                }
            }
        })
    }

    private fun onSuccess(results: ArrayList<Result>, showLoadingScreen: Boolean) {
        mAdapter?.setResult(results, showLoadingScreen)
    }

    private fun onFailure() {
        mAdapter?.resetLastItem()
    }

    private fun getListAdapter(): PaginationAdapter<*, Result>? = when (getListType()) {
        ListType.TYPE_CHARACTERS -> {
            CharacterListAdapter(mLists, this)
        }
        ListType.TYPE_COMICS -> {
            ComicsListAdapter(mLists, this)
        }
        ListType.TYPE_FILTER -> {
            FilterAdapter(mLists, this, true)
        }
        ListType.TYPE_SEARCH -> {
            FilterAdapter(mLists, this, false)
        }
        else -> null
    }

    override fun onLoadMore(position: Int) {
        if (position == 0) {
            mProgressBar.visibility = View.VISIBLE
        }
        mTextView.visibility = View.INVISIBLE
        mBaseViewModel.fetchData(getApi(position), position, getListType())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}