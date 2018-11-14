package io.revze.footballapp.ui.match.next

import android.content.Context
import com.google.gson.Gson
import io.revze.footballapp.api.ApiClient
import io.revze.footballapp.model.League
import io.revze.footballapp.model.LeagueResponse
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class NextMatchPresenterTest {

    @Mock
    private lateinit var view: NextMatchView

    @Mock
    private lateinit var leagueCallback: ApiClient.GetLeagueCallback

    @Mock
    private lateinit var apiClient: ApiClient

    @Mock
    private lateinit var context: Context

    private lateinit var presenter: NextMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchPresenter(view)
    }

    @Test
    fun getLeague() {
        val leagues = mutableListOf<League>()

        presenter.setLeagueCallback(leagueCallback)

        `when`(apiClient.getLeagues(context, leagueCallback))

        presenter.getLeague(context)

        verify(view).onSuccessGetLeague(leagues)
    }

    @Test
    fun getNextMatch() {
    }
}