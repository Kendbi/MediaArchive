package com.example.mediaarchive

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(private val dataArray: ArrayList<MovieRow>, var clickListner: OnMovieDeleteListener) :
    RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            MyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.movie_data, parent, false)




        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataArray.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.movieTitleDB.text =
            dataArray[holder.adapterPosition].movieTitle
        holder.movieDirectorFirstNameDB.text =
            dataArray[holder.adapterPosition].movieDirectorFirstName
        holder.movieDirectorLastNameDB.text =
            dataArray[holder.adapterPosition].moviewDirectorLastName
        holder.movieTypeDB.text =
            dataArray[holder.adapterPosition].moviewType
        holder.movieYearOfPublicationDB.text =
            dataArray[holder.adapterPosition].movieYearOfPublication.toString()
        holder.movieStatusDB.text =
            dataArray[holder.adapterPosition].moviewStatus
        holder.initialize(dataArray.get(position),clickListner)



    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieTitleDB = view.findViewById(R.id.movieTitleDB) as TextView
        val movieDirectorFirstNameDB = view.findViewById(R.id.movieDirectorFirstNameDB) as TextView
        val movieDirectorLastNameDB = view.findViewById(R.id.movieDirectorLastNameDB) as TextView
        val movieTypeDB = view.findViewById(R.id.movieTypeDB) as TextView
        val movieYearOfPublicationDB = view.findViewById(R.id.movieReleaseDateDB) as TextView
        val movieStatusDB = view.findViewById(R.id.movieStatusDB) as TextView

        fun initialize(item: MovieRow, action: OnMovieDeleteListener){
            itemView.setOnClickListener{
                action.onItemClick(item,adapterPosition)
            }
        }
    }
    interface OnMovieDeleteListener{
        fun onItemClick(item: MovieRow, position: Int)
    }
}