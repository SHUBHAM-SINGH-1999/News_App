package com.example.newsapp

import android.content.Context
import android.graphics.drawable.DrawableContainer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide


class myadapter(var list:ArrayList<data>):RecyclerView.Adapter<myadapter.viewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        var inf = LayoutInflater.from(parent.context)
        var n = inf.inflate(R.layout.item,parent,false)
        var vie = viewholder(n)
        n.setOnClickListener{
            Toast.makeText(parent.context,"""Opened from: "${list[vie.adapterPosition].author}"""",Toast.LENGTH_SHORT).show()

            val url = list[vie.adapterPosition].link
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(parent.context, Uri.parse(url))
        }
        return vie
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.title.text = list[position].title
        holder.description.text = list[position].content
        Glide.with(holder.itemView.context).load(list[position].image).into(holder.im)
    }

    override fun getItemCount(): Int {
        return list.size
    }



    class viewholder(itemView: View) :ViewHolder(itemView){
        var title = itemView.findViewById<TextView>(R.id.tit)
        var description = itemView.findViewById<TextView>(R.id.des)
        var im = itemView.findViewById<ImageView>(R.id.image)
    }

}



