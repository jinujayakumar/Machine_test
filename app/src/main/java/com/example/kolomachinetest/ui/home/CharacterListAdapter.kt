package com.example.kolomachinetest.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.kolomachinetest.R
import com.example.kolomachinetest.data.ApiResult
import com.example.kolomachinetest.uils.Utils

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
        val apiResult = list[position]
        holder.textView.text = apiResult.name
        val url = "${apiResult.thumbnail.path}.${apiResult.thumbnail.extension}"
        Glide.with(holder.textView.context)
            .load(url)
            .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            .listener(Utils.loadPallet(holder.itemView, holder.imageView.context))
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
