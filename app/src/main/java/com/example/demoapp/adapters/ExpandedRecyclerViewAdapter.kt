package com.example.demoapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.demoapp.R
import com.example.demoapp.data.model.Movie
import com.google.android.material.imageview.ShapeableImageView

class ExpandedRecyclerViewAdapter : RecyclerView.Adapter<ExpandedRecyclerViewAdapter.MyViewHolder>() {
    private var listData : List<Movie>?=null

    fun setUpdatedData(listData:List<Movie>){
        this.listData=listData
        notifyDataSetChanged()
    }

    class MyViewHolder(view : View):RecyclerView.ViewHolder(view){
        val ShapeableImageView = view.findViewById<ShapeableImageView>(R.id.cardImage)
        val textView = view.findViewById<TextView>(R.id.rating)
        val url : String ="https://image.tmdb.org/t/p/w500"

        fun bind(data:Movie){
            textView.setText(data.voteAverage.toString())
            Glide.with(ShapeableImageView)
                .load(url+data.posterPath)
                .apply(RequestOptions.centerCropTransform())
                .into(ShapeableImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int):MyViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_expanded_image,parent,false)
        return MyViewHolder(view)
    }
    override fun getItemCount():Int{
        if(listData==null)return 0
        else return listData?.size!!
    }

    override fun onBindViewHolder(holder:MyViewHolder,position:Int){
        holder.bind(listData?.get(position)!!)
    }
}