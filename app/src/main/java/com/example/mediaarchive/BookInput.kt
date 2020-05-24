package com.example.mediaarchive

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.book_data.*
import kotlinx.android.synthetic.main.fragment_book_input.*
import kotlinx.android.synthetic.main.fragment_book_input.view.*
import java.util.*
import kotlin.collections.ArrayList

class BookInput : Fragment(), BookAdapter.OnBookDeleteListener {

    private lateinit var databaseReference: DatabaseReference
    private  lateinit var listOfBooks: ArrayList<BookRow>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_book_input, container, false)
        var recyclerViewBook = rootView.findViewById<View>(R.id.recyclerViewBook) as RecyclerView
        recyclerViewBook.layoutManager = GridLayoutManager(context, 1)

        val firebase = FirebaseDatabase.getInstance()
        databaseReference = firebase.getReference("BooksData")

        listOfBooks = ArrayList()
        recyclerViewBook.adapter = BookAdapter(listOfBooks, this)



        var bookUploadBtn = rootView.findViewById<Button>(R.id.bookUploadButton)
        bookUploadBtn.setOnClickListener {
            val id = Date().time.toString()
            val title = bookTitleInput.text.toString()
            val authorFirstName = bookAuthorFirstNameInput.text.toString()
            val authorLastName = bookAuthorLastNameInput.text.toString()
            val type = bookTypeInput.selectedItem.toString()
            val yearOfPublication = bookYearOfPublicationInput.text.toString().toInt()
            val status = bookStatusInput.selectedItem.toString()

            val firebaseInput = BookRow(
                id, title, authorFirstName, authorLastName, type,
                yearOfPublication, status
            )

            databaseReference.child(id).setValue(firebaseInput)

            val toast = Toast.makeText(context, "Poszło", Toast.LENGTH_LONG)
            toast.show()

        }




        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                listOfBooks = ArrayList()

                for (row in dataSnapshot.children) {
                    val newRow = row.getValue(BookRow::class.java)
                    listOfBooks.add(newRow!!)
                }

                setupAdapter(listOfBooks)

            }

            private fun setupAdapter(arrayData: ArrayList<BookRow>) {
                recyclerViewBook.adapter = BookAdapter(arrayData, this@BookInput)
            }


        })

        return rootView
    }


    override fun onItemClick(item: BookRow, position: Int) {
        deleteBook(item.readBookID())
    }

    fun deleteBook(bookID: String){
        databaseReference.child(bookID).removeValue()
        Toast.makeText(context, bookID, Toast.LENGTH_LONG).show()
    }


}
