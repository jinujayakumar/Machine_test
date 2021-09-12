package com.example.kolomachinetest.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.kolomachinetest.R
import com.example.kolomachinetest.api.PaginationCallback
import com.example.kolomachinetest.data.Result
import com.example.kolomachinetest.ui.adapter.PaginationAdapter
import com.example.kolomachinetest.uils.Utils

class CharacterListAdapter(
    list: ArrayList<Result>,
    paginationCallback: PaginationCallback
) :
    PaginationAdapter<CharacterListAdapter.CharacterVH, Result>(list, paginationCallback) {


    class CharacterVH(view: View) : PaginationAdapter.PaginationViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): CharacterVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_list, parent, false)
        return CharacterVH(view)
    }

    override fun onBindViewHolder(holder: CharacterVH, pos: Int, model: Result) {
        holder.textView.text = model.name
        val url = "${model.thumbnail.path}.${model.thumbnail.extension}"
        Glide.with(holder.textView.context)
            .load(url)
            .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            .listener(Utils.loadPallet(holder.itemView, holder.imageView.context))
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.imageView)
    }
}
