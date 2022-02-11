package com.example.capstone

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.data.Test
import com.example.capstone.data.food
import com.example.capstone.data.food_list

class MenuAdapter(val context: Context, val foods: List<food>) : RecyclerView.Adapter<MenuAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_menu, parent, false)
        return ViewHolder(view)
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {

            }
        }
        val menutitle: TextView = itemView.findViewById(R.id.menu_name)
        val menudetail: TextView = itemView.findViewById(R.id.menu_detail)
        val menuimg: ImageView = itemView.findViewById(R.id.menu_img)
        val menurating: TextView = itemView.findViewById(R.id.rating)
        val menuprice: TextView = itemView.findViewById(R.id.menu_price)
    }

    override fun getItemCount(): Int {
        return foods.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var items = foods[position]
        Log.d("recyclerview ", "${items.introduce}/${items.name}/${items.price}/${items.rating}")
        holder.menutitle.text = items.name
        holder.menudetail.text = items.introduce
        holder.menuprice.text = items.price
        holder.menurating.text = items.rating
        holder.menuimg.setImageResource(R.drawable.ic_launcher_background)

        holder.itemView.setOnClickListener {
            val reviews = arrayOf("리뷰 보기","리뷰 작성")
            var builder = AlertDialog.Builder(context)
                .setTitle("ReView")
                .setNegativeButton("취소", null)
                .setItems(reviews) { _, which ->
                    val intent = Intent(holder.itemView?.context, ReviewActivity::class.java)
                    intent.putExtra("name", "${items.name}")
                    ContextCompat.startActivity(holder.itemView.context, intent, null)
                }
                .show()
        }
    }
}