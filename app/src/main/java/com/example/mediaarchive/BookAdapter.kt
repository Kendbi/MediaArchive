package com.example.mediaarchive

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_book_input.view.*

class BookAdapter(private val dataArray: ArrayList<BookRow>, var clickListner: OnBookDeleteListener) :
    RecyclerView.Adapter<BookAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            MyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.book_data, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataArray.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bookTitleDB.text =
            dataArray[holder.adapterPosition].bookTitle
        holder.bookAuthorFirstNameDB.text =
            dataArray[holder.adapterPosition].bookAuthorFirstName
        holder.bookAuthorLastNameDB.text =
            dataArray[holder.adapterPosition].bookAuthorLastName
        holder.bookTypeDB.text =
            dataArray[holder.adapterPosition].bookType
        holder.bookYearOfPublicationDB.text =
            dataArray[holder.adapterPosition].bookYearOfPublication.toString()
        holder.bookStatusDB.text =
            dataArray[holder.adapterPosition].bookStatus
        holder.initialize(dataArray.get(position),clickListner)


    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookTitleDB = view.findViewById(R.id.bookTitleDB) as TextView
        val bookAuthorFirstNameDB = view.findViewById(R.id.bookAuthorFirstNameDB) as TextView
        val bookAuthorLastNameDB = view.findViewById(R.id.bookAuthorLastNameDB) as TextView
        val bookTypeDB = view.findViewById(R.id.bookTypeDB) as TextView
        val bookYearOfPublicationDB = view.findViewById(R.id.bookYearOfPublicationDB) as TextView
        val bookStatusDB = view.findViewById(R.id.bookStatusDB) as TextView

        fun initialize(item: BookRow, action: OnBookDeleteListener){
            itemView.setOnClickListener{
                action.onItemClick(item,adapterPosition)
            }
        }

    }

    interface OnBookDeleteListener{
        fun onItemClick(item: BookRow, position: Int)
    }


}