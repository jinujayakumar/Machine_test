package com.example.kolomachinetest.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kolomachinetest.R
import com.example.kolomachinetest.api.repo.local.Search

class SearchAdapter(var list: List<Search>, var searchFragment: SearchCallBack) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    interface SearchCallBack {
        fun onSearchSelected(string: String?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_list, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.textView.text = list[position].key
        holder.itemView.setOnClickListener {
            searchFragment.onSearchSelected(list[position].key)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
    }
}
