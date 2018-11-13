package io.revze.footballapp.api

import android.content.Context
import io.revze.footballapp.R
import io.revze.footballapp.model.*
import org.jetbrains.anko.longToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class ApiClient {
    private val apiService = ApiServiceInterface.create()

    fun getLeagues(context: Context, getLeagueCallback: GetLeagueCallback) {
        apiService.getLeagues().enqueue(object : Callback<LeagueResponse> {
            override fun onResponse(call: Call<LeagueResponse>, response: Response<LeagueResponse>) {
                if (response.isSuccessful) getLeagueCallback.onSuccess(response.body()?.leagues)
                else getLeagueCallback.onFailed(context.getString(R.string.server_error))
            }

            override fun onFailure(call: Call<LeagueResponse>, t: Throwable) {
                if (t is IOException) getLeagueCallback.onFailed(context.getString(R.string.network_error))
                else getLeagueCallback.onFailed(context.getString(R.string.unknown_error))
            }
        })
    }

    interface GetLeagueCallback {
        fun onSuccess(leagues: List<League>?)

        fun onFailed(message: String)
    }

    fun getNextMatches(context: Context, leagueId: String, getNextMatchCallback: GetNextMatchCallback) {
        apiService.getNextMatch(leagueId).enqueue(object : Callback<NextMatchResponse> {
            override fun onResponse(call: Call<NextMatchResponse>, response: Response<NextMatchResponse>) {
                if (response.isSuccessful) getNextMatchCallback.onSuccess(response.body()?.nextMatch)
                else getNextMatchCallback.onFailed(context.getString(R.string.server_error))
            }

            override fun onFailure(call: Call<NextMatchResponse>, t: Throwable) {
                if (t is IOException) getNextMatchCallback.onFailed(context.getString(R.string.network_error))
                else getNextMatchCallback.onFailed(context.getString(R.string.unknown_error))
            }
        })
    }

    interface GetNextMatchCallback {
        fun onSuccess(nextMatches: List<NextMatch>?)

        fun onFailed(message: String)
    }

    fun getLastMatches(context: Context, leagueId: String, getLastMatchCallback: GetLastMatchCallback) {
        apiService.getLastMatch(leagueId).enqueue(object : Callback<LastMatchResponse> {
            override fun onResponse(call: Call<LastMatchResponse>, response: Response<LastMatchResponse>) {
                if (response.isSuccessful) getLastMatchCallback.onSuccess(response.body()?.lastMatch)
                else getLastMatchCallback.onFailed(context.getString(R.string.server_error))
            }

            override fun onFailure(call: Call<LastMatchResponse>, t: Throwable) {
                if (t is IOException) getLastMatchCallback.onFailed(context.getString(R.string.network_error))
                else getLastMatchCallback.onFailed(context.getString(R.string.unknown_error))
            }
        })
    }

    interface GetLastMatchCallback {
        fun onSuccess(lastMatches: List<LastMatch>?)

        fun onFailed(message: String)
    }

    fun searchMatches(context: Context, query: String, searchMatchCallback: SearchMatchCallback) {
        apiService.searchMatch(query).enqueue(object : Callback<SearchMatchResponse> {
            override fun onResponse(call: Call<SearchMatchResponse>, response: Response<SearchMatchResponse>) {
                if (response.isSuccessful) searchMatchCallback.onSuccess(response.body()?.match)
                else searchMatchCallback.onFailed(context.getString(R.string.server_error))
            }

            override fun onFailure(call: Call<SearchMatchResponse>, t: Throwable) {
                if (t is IOException) searchMatchCallback.onFailed(context.getString(R.string.network_error))
                else searchMatchCallback.onFailed(context.getString(R.string.unknown_error))
            }
        })
    }

    interface SearchMatchCallback {
        fun onSuccess(matches: List<SearchMatch>?)

        fun onFailed(message: String)
    }

    fun getMatchDetail(context: Context, matchId: String, getMatchDetailCallback: GetMatchDetailCallback) {
        apiService.getMatchDetail(matchId).enqueue(object : Callback<MatchDetailResponse> {
            override fun onResponse(call: Call<MatchDetailResponse>, response: Response<MatchDetailResponse>) {
                if (response.isSuccessful) getMatchDetailCallback.onSuccess(response.body()?.matchDetail?.get(0))
                else getMatchDetailCallback.onFailed(context.getString(R.string.server_error))
            }

            override fun onFailure(call: Call<MatchDetailResponse>, t: Throwable) {
                if (t is IOException) getMatchDetailCallback.onFailed(context.getString(R.string.network_error))
                else getMatchDetailCallback.onFailed(context.getString(R.string.unknown_error))
            }
        })
    }

    interface GetMatchDetailCallback {
        fun onSuccess(matchDetail: MatchDetail?)

        fun onFailed(message: String)
    }

    fun getTeamDetail(context: Context, teamId: String, getTeamDetailCallback: GetTeamDetailCallback) {
        apiService.getTeamDetail(teamId).enqueue(object : Callback<TeamDetailResponse> {
            override fun onResponse(call: Call<TeamDetailResponse>, response: Response<TeamDetailResponse>) {
                if (response.isSuccessful) getTeamDetailCallback.onSuccess(response.body()?.teams?.get(0))
                else getTeamDetailCallback.onFailed(context.getString(R.string.server_error))
            }

            override fun onFailure(call: Call<TeamDetailResponse>, t: Throwable) {
                if (t is IOException) getTeamDetailCallback.onFailed(context.getString(R.string.network_error))
                else getTeamDetailCallback.onFailed(context.getString(R.string.unknown_error))
            }
        })
    }

    interface GetTeamDetailCallback {
        fun onSuccess(team: Team?)

        fun onFailed(message: String)
    }

    fun getTeams(context: Context, leagueId: String, getTeamsCallback: GetTeamsCallback) {
        apiService.getTeams(leagueId).enqueue(object : Callback<TeamsResponse> {
            override fun onResponse(call: Call<TeamsResponse>, response: Response<TeamsResponse>) {
                if (response.isSuccessful) getTeamsCallback.onSuccess(response.body()?.teams)
                else getTeamsCallback.onFailed(context.getString(R.string.server_error))
            }

            override fun onFailure(call: Call<TeamsResponse>, t: Throwable) {
                if (t is IOException) getTeamsCallback.onFailed(context.getString(R.string.network_error))
                else getTeamsCallback.onFailed(context.getString(R.string.unknown_error))
            }
        })
    }

    interface GetTeamsCallback {
        fun onSuccess(teams: List<Team>?)

        fun onFailed(message: String)
    }

    fun searchTeam(context: Context, name: String, searchTeamCallback: SearchTeamCallback) {
        apiService.searchTeam(name).enqueue(object : Callback<SearchTeamResponse> {
            override fun onResponse(call: Call<SearchTeamResponse>, response: Response<SearchTeamResponse>) {
                if (response.isSuccessful) searchTeamCallback.onSuccess(response.body()?.teams)
                else searchTeamCallback.onFailed(context.getString(R.string.server_error))
            }

            override fun onFailure(call: Call<SearchTeamResponse>, t: Throwable) {
                if (t is IOException) searchTeamCallback.onFailed(context.getString(R.string.network_error))
                else searchTeamCallback.onFailed(context.getString(R.string.unknown_error))
            }
        })
    }

    interface SearchTeamCallback {
        fun onSuccess(team: List<Team>?)

        fun onFailed(message: String)
    }

    fun getPlayer(context: Context, teamId: String, getPlayerCallback: GetPlayerCallback) {
        apiService.getPlayer(teamId).enqueue(object : Callback<PlayersResponse> {
            override fun onResponse(call: Call<PlayersResponse>, response: Response<PlayersResponse>) {
                if (response.isSuccessful) getPlayerCallback.onSuccess(response.body()?.players)
                else getPlayerCallback.onFailed(context.getString(R.string.server_error))
            }

            override fun onFailure(call: Call<PlayersResponse>, t: Throwable) {
                if (t is IOException) getPlayerCallback.onFailed(context.getString(R.string.network_error))
                else getPlayerCallback.onFailed(context.getString(R.string.unknown_error))
            }
        })
    }

    interface GetPlayerCallback {
        fun onSuccess(player: List<Player>?)

        fun onFailed(message: String)
    }
}