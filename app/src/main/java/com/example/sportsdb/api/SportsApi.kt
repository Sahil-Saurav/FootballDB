package com.example.sportsdb.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SportsApi {

    @GET("searchteams.php")
    suspend fun getTeam(
        @Query("t") team :String
    ):Response <TeamDetails>
    @GET("search_all_teams.php")
    suspend fun getLeague(
        @Query("l") league :String
    ):Response <LeagueDetails>
    @GET("lookuptable.php")
    suspend fun getStandings(
        @Query("l") leagueId:String,
        @Query("s") season:String
    ):Response <Standings>
}