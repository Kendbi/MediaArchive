package com.example.mediaarchive

class GameRow (val gameID: String = "",val gameTitle: String = "",  val gameDeveloperName: String = "", val gameType: String = "",
    val gameYearOfPublication: Int = 0, val gameStatus: String = ""){
    public fun readGameID(): String{
        return gameID
    }
}