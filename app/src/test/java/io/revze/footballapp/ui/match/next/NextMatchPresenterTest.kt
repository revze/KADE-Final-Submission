package io.revze.footballapp.ui.match.next

import android.content.Context
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import io.revze.footballapp.api.ApiServiceInterface
import io.revze.footballapp.model.League
import io.revze.footballapp.model.LeagueResponse
import io.revze.footballapp.model.NextMatch
import io.revze.footballapp.model.NextMatchResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class NextMatchPresenterTest {

    @Mock
    private lateinit var view: NextMatchView

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var apiServiceInterface: ApiServiceInterface

    private lateinit var presenter: NextMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchPresenter(view, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Test
    fun testGetNextMatch() {
        val matchId = "123456"
        val nextMatchResponse = NextMatchResponse(null)

        `when`(apiServiceInterface.getNextMatch(matchId)).thenReturn(Observable.just(nextMatchResponse))

        presenter.getNextMatch(context, matchId)

        verify(view).showLoader()
        verify(view).hideLoader()
        verify(view).onSuccessGetNextMatch(nextMatchResponse.nextMatch)
    }
}