package io.revze.footballapp.ui.match.next


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import io.revze.footballapp.R
import io.revze.footballapp.adapter.NextMatchAdapter
import io.revze.footballapp.model.League
import io.revze.footballapp.model.NextMatch
import io.revze.footballapp.utils.gone
import io.revze.footballapp.utils.visible
import kotlinx.android.synthetic.main.fragment_next_match.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.layout_loader.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.longToast
import org.jetbrains.anko.support.v4.onRefresh

/**
 * A simple [Fragment] subclass.
 *
 */
class NextMatchFragment : Fragment(), NextMatchView {

    private val nextMatches = mutableListOf<NextMatch>()
    private val leagues = mutableListOf<League>()
    private val leagueNames = mutableListOf<String>()
    private lateinit var nextMatchAdapter: NextMatchAdapter
    private lateinit var leagueAdapter: ArrayAdapter<String>
    private lateinit var presenter: NextMatchPresenter
    private lateinit var rvNextMatch: RecyclerView
    private lateinit var layoutLoader: LinearLayout
    private lateinit var layoutError: LinearLayout
    private lateinit var tvError: TextView
    private lateinit var btnRefresh: Button
    private var leagueId = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = NextMatchPresenter(this)
        val spinnerLeague = spinner_league_next_match
        rvNextMatch = rv_next_match
        layoutLoader = layout_loader
        layoutError = layout_error
        tvError = tv_error
        btnRefresh = btn_refresh
        nextMatchAdapter = NextMatchAdapter(requireContext(), nextMatches)
        rvNextMatch.layoutManager = LinearLayoutManager(context)
        rvNextMatch.adapter = nextMatchAdapter
        leagueAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, leagueNames)

        leagueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerLeague.adapter = leagueAdapter
        spinnerLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueId = leagues[position].id
                presenter.getNextMatch(requireContext(), leagues[position].id)
            }
        }
        presenter.getLeague(requireContext())
        btnRefresh.onClick { refreshNextMatch() }
    }

    override fun onDetachView() {
        presenter.onDetach()
    }

    override fun onDestroy() {
        onDetachView()
        super.onDestroy()
    }

    override fun onSuccessGetLeague(leagues: List<League>?) {
        this.leagues.addAll(leagues as MutableList)
        for (i in leagues.indices) leagueNames.add(leagues[i].name)

        leagueAdapter.notifyDataSetChanged()
    }

    override fun onFailedGetLeague(message: String) {
        longToast(message)
    }

    override fun showLoader() {
        layoutLoader.visible()
        layoutError.gone()
        rvNextMatch.gone()
    }

    override fun hideLoader() {
        layoutLoader.gone()
    }

    override fun onFailedGetNextMatch(message: String) {
        rvNextMatch.gone()
        layoutError.visible()
        tvError.text = message
    }

    override fun onSuccessGetNextMatch(nextMatches: List<NextMatch>?) {
        if (nextMatches != null) {
            rvNextMatch.visible()
            layoutError.gone()

            this.nextMatches.clear()
            this.nextMatches.addAll(nextMatches as MutableList)
            nextMatchAdapter.notifyDataSetChanged()
        } else {
            onFailedGetNextMatch(requireContext().getString(R.string.empty_next_match))
        }
    }

    private fun refreshNextMatch() {
        showLoader()

        presenter.getNextMatch(requireContext(), leagueId)
    }
}
