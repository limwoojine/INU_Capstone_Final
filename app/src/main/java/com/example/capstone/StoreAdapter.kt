package com.example.capstone

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class StoreAdapter(private val stores: List<Test>) : RecyclerView.Adapter<StoreAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent, false)
        return ViewHolder(view)
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {

            }
        }
        val storetitle: TextView = itemView.findViewById(R.id.store_name)
        val storedetail: TextView = itemView.findViewById(R.id.store_detail)
        val storeimg: ImageView = itemView.findViewById(R.id.store_img)
    }

    override fun getItemCount(): Int {
        return stores.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var items = stores[position]
        Log.d("recyclerview ", "${items.introduce}/${items.name}")
        holder.storetitle.text = items.name
        holder.storedetail.text = items.introduce
        holder.storeimg.setImageResource(R.drawable.ic_launcher_background)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView?.context, MenuActivity::class.java)
            intent.putExtra("name","${items.name}")
            intent.putExtra("Id", "${items.id}")
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }
}