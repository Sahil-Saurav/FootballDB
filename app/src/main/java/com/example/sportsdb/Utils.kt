package com.example.sportsdb

class Utils {
    fun replaceSpaces(input: String): String {
        return input.replace(" ", "%20")
    }
    fun leagueToId(league:String):String{
        val id = when(league){
            "Premier League"-> "4328"
            "La Liga"-> "4335"
            "Bundesliga" -> "4331"
            "Serie A" -> "4332"
            "Ligue 1" -> "4334"
            else -> "null"
        }
        return id
    }
}