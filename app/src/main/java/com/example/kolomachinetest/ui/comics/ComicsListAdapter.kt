package com.example.kolomachinetest.ui.comics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.kolomachinetest.R
import com.example.kolomachinetest.api.PaginationCallback
import com.example.kolomachinetest.data.Price
import com.example.kolomachinetest.data.Result
import com.example.kolomachinetest.ui.adapter.PaginationAdapter
import com.example.kolomachinetest.uils.Utils

class ComicsListAdapter(
    list: ArrayList<Result>,
    paginationCallback: PaginationCallback
) :
    PaginationAdapter<ComicsListAdapter.ComicsViewHolder, Result>(list, paginationCallback) {


    class ComicsViewHolder(view: View) : PaginationAdapter.PaginationViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
        val price: TextView = view.findViewById(R.id.price)
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comics_list, parent, false)
        return ComicsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, pos: Int, model: Result) {
        holder.textView.text = model.title
        holder.price.text = getPrice(model.prices)
        val url = "${model.thumbnail.path}.${model.thumbnail.extension}"
        Glide.with(holder.textView.context)
            .load(url)
            .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            .listener(Utils.loadPallet(holder.itemView, holder.imageView.context))
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.imageView)
    }

    private fun getPrice(prices: List<Price>): CharSequence? {
        var price = ""
        if (prices.isNotEmpty() && prices[0] != null) {
            price = "${prices[0].price}";
        }
        return price
    }


}