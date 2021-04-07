package com.example.queenapi

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.queenapi.model.Queen
import com.squareup.picasso.Picasso
import java.lang.RuntimeException

class QueenAdapter(val queens: List<Queen>):RecyclerView.Adapter<QueenAdapter.QueenViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueenViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_recycle,parent,false)
        return QueenViewHolder(view)
    }

    override fun onBindViewHolder(holder: QueenViewHolder, position: Int) {
        holder.name.text = queens[position].name
//        val url = if(queens[position].imageUrl?.get(5) == 's'){
//            queens[position].imageUrl
//        }
//        else{
//            queens[position].imageUrl?.replace("http", "https")
//        }
        Log.d("position","$position")
        Picasso.get().load(queens[position].imageUrl).into(holder.image)

        holder.itemView.setOnClickListener{
            val intent = Intent(it.context,DetailActivity::class.java)
            intent.putExtra("IMAGE",queens[position].imageUrl)
            intent.putExtra("QUOTE",queens[position].quote)
            intent.putExtra("WINNER",queens[position].winner)
            intent.putExtra("MISS",queens[position].missCongeniality)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount() = queens.size

    inner class QueenViewHolder(view: View):RecyclerView.ViewHolder(view){
        val image: ImageView by lazy { view.findViewById<ImageView>(R.id.characterImage) }
        val name: TextView by lazy { view.findViewById<TextView>(R.id.name) }
    }
}