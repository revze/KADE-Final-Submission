package io.revze.footballapp.api

import io.revze.footballapp.model.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceInterface {
    @GET("all_leagues.php")
    fun getLeagues(): Call<LeagueResponse>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") leagueId: String): Call<NextMatchResponse>

    @GET("eventspastleague.php")
    fun getLastMatch(@Query("id") leagueId: String): Call<LastMatchResponse>

    @GET("searchevents.php")
    fun searchMatch(@Query("e") query: String): Call<SearchMatchResponse>

    @GET("lookupevent.php")
    fun getMatchDetail(@Query("id") matchId: String): Call<MatchDetailResponse>

    @GET("lookupteam.php")
    fun getTeamDetail(@Query("id") teamId: String): Call<TeamDetailResponse>

    @GET("lookup_all_teams.php")
    fun getTeams(@Query("id") leagueId: String): Call<TeamsResponse>

    @GET("searchteams.php")
    fun searchTeam(@Query("t") name: String): Call<SearchTeamResponse>

    @GET("lookup_all_players.php")
    fun getPlayer(@Query("id") teamId: String): Call<PlayersResponse>

    companion object Factory {
        fun create(): ApiServiceInterface {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
                    .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}