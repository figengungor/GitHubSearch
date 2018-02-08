package com.figengungor.githubsearch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by figengungor on 2/6/2018.
 */
class RepositoryAdapter(private val items: ArrayList<Item>) : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    fun clearData() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repositoy, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class RepositoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleTv = view.findViewById<TextView>(R.id.titleTv)
        private val descriptionTv = view.findViewById<TextView>(R.id.descriptionTv)
        private val urlTv = view.findViewById<TextView>(R.id.urlTv)
        private val userPhotoIv = view.findViewById<ImageView>(R.id.userPhotoIv)

        fun bindItem(item: Item) {
            with(item) {
                titleTv.text = name
                descriptionTv.text = description
                urlTv.text = url
                userPhotoIv.loadUrl(owner.avatarUrl)
            }
        }
    }

}