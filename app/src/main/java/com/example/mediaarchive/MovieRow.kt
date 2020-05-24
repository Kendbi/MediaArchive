package com.example.mediaarchive

class MovieRow (val movieID: String = "", val movieTitle: String = "", val movieDirectorFirstName: String = "",
                val moviewDirectorLastName: String = "", val moviewType: String = "",
                val movieYearOfPublication: Int = 0, val moviewStatus: String = ""){
    public fun readMovieID(): String{
        return movieID
    }
}