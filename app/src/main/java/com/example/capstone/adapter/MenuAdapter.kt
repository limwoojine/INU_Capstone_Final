package com.example.capstone.adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.R
import com.example.capstone.data.Food

class MenuAdapter(private val foods: List<Food.food_list.FoodListDto>) : RecyclerView.Adapter<MenuAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.food_list, parent, false)
        return ViewHolder(view)
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val menuName: TextView = itemView.findViewById(R.id.menu_name)
        val menuDetail: TextView = itemView.findViewById(R.id.menu_detail)
        val menuImg: ImageView = itemView.findViewById(R.id.menu_img)
        val menuRating: TextView = itemView.findViewById(R.id.rating)
        val menuPrice: TextView = itemView.findViewById(R.id.menu_price)
    }

    override fun getItemCount(): Int {
        Log.d("리턴 사이즈", "${foods?.size}")
        return foods!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var items = foods!!.get(position)
        Log.d("recyclerview ", "${items.name}/${items.price}/${items.status}")
        holder.menuName.text = items?.name
        holder.menuPrice.text = items?.price
        holder.menuRating.text = 3.0.toString()
        holder.menuImg.setImageResource(R.drawable.ic_launcher_background)


    }
}