package com.example.mediaarchive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main_menu.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val fm:FragmentManager = supportFragmentManager
        val bookInput = BookInput()
        val movieInput = MovieInput()
        val gameInput = GameInput()

        go_to_book.setOnClickListener{
            fm.beginTransaction().add(R.id.fragment_main, bookInput).addToBackStack(null).commit()
        }
        go_to_games.setOnClickListener{
            fm.beginTransaction().add(R.id.fragment_main, gameInput).addToBackStack(null).commit()
        }
        go_to_movies.setOnClickListener{
            fm.beginTransaction().add(R.id.fragment_main, movieInput).addToBackStack(null).commit()
        }

    }
}
