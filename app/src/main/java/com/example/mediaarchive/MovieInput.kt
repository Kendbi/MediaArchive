package com.example.mediaarchive

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
import kotlinx.android.synthetic.main.fragment_movie_input.*
import java.util.*
import kotlin.collections.ArrayList

class MovieInput : Fragment(), MovieAdapter.OnMovieDeleteListener {

    private lateinit var databaseReference: DatabaseReference
    private  lateinit var listOfMovies: ArrayList<MovieRow>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_movie_input, container, false)
        var recyclerViewMovie = rootView.findViewById<View>(R.id.recyclerViewMovie) as RecyclerView
        recyclerViewMovie.layoutManager = GridLayoutManager(context, 1)

        val firebase = FirebaseDatabase.getInstance()
        databaseReference = firebase.getReference("MoviesData")

        listOfMovies = ArrayList()
        recyclerViewMovie.adapter = MovieAdapter(listOfMovies, this)


        var movieUploadBtn = rootView.findViewById<Button>(R.id.movieUploadButton)
        movieUploadBtn.setOnClickListener {
            val id = Date().time.toString()
            val title = movieTitle.text.toString()
            val directorFirstName = movieDirectorFirstName.text.toString()
            val directorLastName = movieDirectorLastName.text.toString()
            val type = movieTypeSpinner.selectedItem.toString()
            val yearOfPublication = movieYearOfPublication.text.toString().toInt()
            val status = movieStatusSpinner.selectedItem.toString()

            val firebaseInput = MovieRow(
                id, title, directorFirstName, directorLastName, type,
                yearOfPublication, status
            )

            databaseReference.child(id).setValue(firebaseInput)


        }


        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                listOfMovies = ArrayList()

                for (row in dataSnapshot.children) {
                    val newRow = row.getValue(MovieRow::class.java)
                    listOfMovies.add(newRow!!)
                }

                setupAdapter(listOfMovies)

            }

            private fun setupAdapter(arrayData: ArrayList<MovieRow>) {
                recyclerViewMovie.adapter = MovieAdapter(arrayData, this@MovieInput)
            }

        })

        return rootView
    }
    override fun onItemClick(item: MovieRow, position: Int) {
        deleteMovie(item.readMovieID())
    }

    fun deleteMovie(movieID: String){
        databaseReference.child(movieID).removeValue()
        Toast.makeText(context, "Usunięto", Toast.LENGTH_LONG).show()
    }

}
