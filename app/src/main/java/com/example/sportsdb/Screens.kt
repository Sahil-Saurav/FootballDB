package com.example.sportsdb

sealed class Screens(val route:String) {
    object Home : Screens("home")
    object LeagueSearch : Screens("search")
    object Standings : Screens("standings")
    object TeamDetails:Screens("teamdetails")
    object LeagueList:Screens("leaguelist")
    object Table:Screens("table")
}