package com.example.kolomachinetest.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kolomachinetest.R
import com.example.kolomachinetest.data.ApiResult

class CharacterListAdapter(private val list: ArrayList<ApiResult>) :
    RecyclerView.Adapter<CharacterListAdapter.CharacterVH>() {


    class CharacterVH(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CharacterVH(view)
    }

    override fun onBindViewHolder(holder: CharacterVH, position: Int) {
        holder.textView.text = list[position].name
        Glide.with(holder.textView.context)
            .load(list[position].thumbnail.path + list[position].thumbnail.extension)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.imageView);
    }

    override fun getItemCount() = list.size

    fun setResult(results: ArrayList<ApiResult>) {
        val size = results.size
        val oldSize = list.size
        list.addAll(results)
        notifyItemRangeInserted(oldSize, size)
    }
}
