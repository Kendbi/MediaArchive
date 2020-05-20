package com.example.mediaarchive

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GameAdapter(private val dataArray: ArrayList<GameRow>) :
    RecyclerView.Adapter<GameAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            MyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.game_data, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataArray.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.gameTitleDB.text =
            dataArray[holder.adapterPosition].gameTitle
        holder.gameDeveloperDB.text =
            dataArray[holder.adapterPosition].gameDeveloperName
        holder.gameTypeDB.text =
            dataArray[holder.adapterPosition].gameType
        holder.gameYearOfPublicationDB.text =
            dataArray[holder.adapterPosition].gameYearOfPublication.toString()
        holder.gameStatusDB.text =
            dataArray[holder.adapterPosition].gameStatus


    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val gameTitleDB = view.findViewById(R.id.gameTitleDB) as TextView
        val gameDeveloperDB = view.findViewById(R.id.gameDeveloperDB) as TextView
        val gameTypeDB = view.findViewById(R.id.gameTypeDB) as TextView
        val gameYearOfPublicationDB = view.findViewById(R.id.gameYearOfPublicationDB) as TextView
        val gameStatusDB = view.findViewById(R.id.gameStatusDB) as TextView
    }
}