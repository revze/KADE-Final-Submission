package io.revze.footballapp.ui.match.last

import android.content.Context
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.revze.footballapp.api.ApiServiceInterface
import io.revze.footballapp.model.LastMatchResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class LastMatchPresenterTest {

    @Mock
    private lateinit var view: LastMatchView

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var apiServiceInterface: ApiServiceInterface

    private lateinit var presenter: LastMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LastMatchPresenter(view, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Test
    fun testGetLastMatch() {
        val matchId = "123456"
        val lastMatchResponse = LastMatchResponse(null)

        `when`(apiServiceInterface.getLastMatch(matchId)).thenReturn(Observable.just(lastMatchResponse))

        presenter.getLastMatch(context, matchId)

        verify(view).showLoader()
        verify(view).hideLoader()
        verify(view).onSuccessGetLastMatch(lastMatchResponse.lastMatch)
    }
}