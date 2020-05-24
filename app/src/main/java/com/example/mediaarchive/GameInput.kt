package com.example.mediaarchive

import android.icu.text.CaseMap
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
import kotlinx.android.synthetic.main.fragment_book_input.*
import kotlinx.android.synthetic.main.fragment_game_input.*
import kotlinx.android.synthetic.main.game_data.*
import java.util.*
import kotlin.collections.ArrayList


class GameInput : Fragment(), GameAdapter.OnGameDeleteListener {

    private lateinit var databaseReference: DatabaseReference
    private  lateinit var listOfGames: ArrayList<GameRow>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_game_input, container, false)
        var recyclerViewGame = rootView.findViewById<View>(R.id.recyclerViewGame) as RecyclerView
        recyclerViewGame.layoutManager = GridLayoutManager(context, 1)

        val firebase = FirebaseDatabase.getInstance()
        databaseReference = firebase.getReference("GamesData")

        listOfGames = ArrayList()
        recyclerViewGame.adapter = GameAdapter(listOfGames, this)



        var gameUploadBtn = rootView.findViewById<Button>(R.id.gameUploadButton)
        gameUploadBtn.setOnClickListener {
            val id = Date().time.toString()
            val title = gameTitleInput.text.toString()
            val developerName = developerNameInput.text.toString()
            val type = gameTypeInput.selectedItem.toString()
            val yearOfPublication = gameYearOfPublicationInput.text.toString().toInt()
            val status = gameStatusInput.selectedItem.toString()

            val firebaseInput = GameRow(
                id, title, developerName, type,
                yearOfPublication, status
            )

            databaseReference.child(id).setValue(firebaseInput)


        }



        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                listOfGames = ArrayList()

                for (row in dataSnapshot.children) {
                    val newRow = row.getValue(GameRow::class.java)
                    listOfGames.add(newRow!!)
                }

                setupAdapter(listOfGames)

            }

            private fun setupAdapter(arrayData: ArrayList<GameRow>) {
                recyclerViewGame.adapter = GameAdapter(arrayData, this@GameInput)
            }

        })



        return rootView
    }
    override fun onItemClick(item: GameRow, position: Int) {
        deleteGame(item.readGameID())
    }

    fun deleteGame(gameID: String){
        databaseReference.child(gameID).removeValue()
        Toast.makeText(context, "Usunięto", Toast.LENGTH_LONG).show()
    }


}
