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

        fm.beginTransaction().add(R.id.mainAct, mainMenu).addToBackStack(null).commit()



    }

    fun goToBook(){
        val fm:FragmentManager = supportFragmentManager
        val bookInput: Fragment = BookInput()
        fm.beginTransaction().replace(R.id.mainAct, bookInput).addToBackStack(null).commit()

    }
    fun goToGame(){
        val fm:FragmentManager = supportFragmentManager
        val gameInput: Fragment = GameInput()
        fm.beginTransaction().replace(R.id.mainAct, gameInput).addToBackStack(null).commit()

    }
    fun goToMovie(){
        val fm:FragmentManager = supportFragmentManager
        val movieInput: Fragment = MovieInput()
        fm.beginTransaction().replace(R.id.mainAct, movieInput).addToBackStack(null).commit()
    }

}
