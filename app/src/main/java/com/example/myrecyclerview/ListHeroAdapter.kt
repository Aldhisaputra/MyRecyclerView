package com.example.myrecyclerview

import android.media.browse.MediaBrowser
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListHeroAdapter(val listHero: ArrayList<Hero>) : RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickcallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickcallback){
        this.onItemClickCallback=onItemClickCallback
    }
    override fun onCreateViewHolder(viewGroup:ViewGroup, i : Int): ListViewHolder {
        val view:View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_hero,viewGroup,false)
        return ListViewHolder(view)

    }



    override fun onBindViewHolder(holder: ListViewHolder,position: Int) {
        val hero = listHero[position]

        Glide.with(holder.itemView.context)
            .load(hero.photo)
            .apply (RequestOptions().override(55,55))
            .into(holder.imagePhoto)

        holder.tvName.text= hero.name
        holder.tvFrom.text= hero.from

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listHero[holder.adapterPosition]) }


    }

    override fun getItemCount(): Int  {
        return listHero.size

    }

    inner class ListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    var tvName:TextView=itemView.findViewById(R.id.tv_item_name)
    var tvFrom:TextView=itemView.findViewById(R.id.tv_item_from)
        var imagePhoto: ImageView=itemView.findViewById(R.id.img_item_photo)

    }

    interface OnItemClickcallback{
        fun onItemClicked(data:Hero)
    }
}