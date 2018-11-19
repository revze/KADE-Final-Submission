package io.revze.footballapp.api

import io.reactivex.Observable
import io.revze.footballapp.model.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceInterface {
    @GET("all_leagues.php")
    fun getLeagues(): Observable<LeagueResponse>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") leagueId: String): Observable<NextMatchResponse>

    @GET("eventspastleague.php")
    fun getLastMatch(@Query("id") leagueId: String): Observable<LastMatchResponse>

    @GET("searchevents.php")
    fun searchMatch(@Query("e") query: String): Observable<SearchMatchResponse>

    @GET("lookupevent.php")
    fun getMatchDetail(@Query("id") matchId: String): Observable<MatchDetailResponse>

    @GET("lookupteam.php")
    fun getTeamDetail(@Query("id") teamId: String): Observable<TeamDetailResponse>

    @GET("lookup_all_teams.php")
    fun getTeams(@Query("id") leagueId: String): Observable<TeamsResponse>

    @GET("searchteams.php")
    fun searchTeam(@Query("t") name: String): Observable<SearchTeamResponse>

    @GET("lookup_all_players.php")
    fun getPlayer(@Query("id") teamId: String): Observable<PlayersResponse>

    companion object {
        fun create(): ApiServiceInterface {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
                    .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}