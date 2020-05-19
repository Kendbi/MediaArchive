package com.example.mediaarchive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main_menu.*
import java.util.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainMenu = main_menu()
        val fm:FragmentManager = supportFragmentManager
        val bookInput: Fragment = BookInput()
        val movieInput = MovieInput()
        val gameInput = GameInput()

        //val bookBtn: Button = findViewById(R.id.go_to_book)
        //val gameBtn: Button = findViewById(R.id.go_to_games)
        //val movieBtn: Button = findViewById(R.id.go_to_movies)
        fm.beginTransaction().add(R.id.mainAct, mainMenu).addToBackStack(null).commit()

        /*bookBtn.setOnClickListener{
            fm.beginTransaction().add(R.id.mainAct, bookInput).addToBackStack(null).commit()
            val toast = Toast.makeText(applicationContext, "Poszło", Toast.LENGTH_LONG)
            toast.show()

        }
        gameBtn.setOnClickListener{
            fm.beginTransaction().replace(R.id.mainAct, gameInput).addToBackStack(null).commit()
        }
        movieBtn.setOnClickListener{
            fm.beginTransaction().replace(R.id.mainAct, movieInput).addToBackStack(null).commit()
        }*/


    }

    public fun goToBook(){
        val fm:FragmentManager = supportFragmentManager
        val bookInput: Fragment = BookInput()
        fm.beginTransaction().replace(R.id.mainAct, bookInput).addToBackStack(null).commit()
        val toast = Toast.makeText(applicationContext, "Poszło", Toast.LENGTH_LONG)
        toast.show()

    }

}
