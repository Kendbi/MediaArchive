package com.example.mediaarchive

data class BookRow(val bookID: String = "", val bookTitle: String = "", val bookAuthorFirstName: String = "",
    val bookAuthorLastName: String = "", val bookType: String = "",
    val bookYearOfPublication: Int = 0, val bookStatus: String = ""){

    public fun readBookID(): String {
        return bookID
    }
}

