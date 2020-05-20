package com.example.mediaarchive

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_main_menu.*


class main_menu : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_main_menu, container, false)

        var logoutBtn = rootView.findViewById<Button>(R.id.logout_button)
        logoutBtn.setOnClickListener {
            (activity as MainActivity).logout()
        }

        var bookBtn = rootView.findViewById<Button>(R.id.go_to_book)
        bookBtn.setOnClickListener{
            (activity as MainActivity).goToBook()
        }

        var gameBtn = rootView.findViewById<Button>(R.id.go_to_games)
        gameBtn.setOnClickListener{
            (activity as MainActivity).goToGame()
        }

        var movieBtn = rootView.findViewById<Button>(R.id.go_to_movies)
        movieBtn.setOnClickListener{
            (activity as MainActivity).goToMovie()
        }

        return rootView
    }
}
