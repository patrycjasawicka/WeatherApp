package com.example.weatherapp.domain

object RegexUtils {
    val polishCharsRegex = "^[a-zA-ZąćęłńóśżźĄĆĘŁŃÓŚŻŹ(), ]*$".toRegex()
}