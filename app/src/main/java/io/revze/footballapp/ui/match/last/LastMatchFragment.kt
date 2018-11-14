package io.revze.footballapp.ui.match.last


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import io.revze.footballapp.R
import io.revze.footballapp.adapter.LastMatchAdapter
import io.revze.footballapp.model.LastMatch
import io.revze.footballapp.model.League
import io.revze.footballapp.utils.gone
import io.revze.footballapp.utils.visible
import kotlinx.android.synthetic.main.fragment_last_match.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.layout_loader.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.longToast
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 *
 */
class LastMatchFragment : Fragment(), LastMatchView {

    private val leagues = mutableListOf<League>()
    private val leagueNames = mutableListOf<String>()
    private val lastMatches = mutableListOf<LastMatch>()
    private lateinit var leagueAdapter: ArrayAdapter<String>
    private lateinit var lastMatchAdapter: LastMatchAdapter
    private lateinit var presenter: LastMatchPresenter
    private lateinit var rvLastMatch: RecyclerView
    private lateinit var layoutLoader: LinearLayout
    private lateinit var layoutError: LinearLayout
    private lateinit var tvError: TextView
    private lateinit var btnRefresh: Button
    private var leagueId = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_last_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = LastMatchPresenter(this)
        val spinnerLeague = spinner_league_last_match
        leagueAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, leagueNames)
        rvLastMatch = rv_last_match
        layoutLoader = layout_loader
        layoutError = layout_error
        tvError = tv_error
        btnRefresh = btn_refresh
        rvLastMatch.layoutManager = LinearLayoutManager(context)
        lastMatchAdapter = LastMatchAdapter(requireContext(), lastMatches)
        rvLastMatch.adapter = lastMatchAdapter

        leagueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerLeague.adapter = leagueAdapter
        spinnerLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueId = leagues[position].id
                presenter.getLastMatch(requireContext(), leagues[position].id)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        presenter.getLeague(requireContext())
        btnRefresh.onClick { refreshLastMatch() }
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
        rvLastMatch.gone()
    }

    override fun hideLoader() {
        layoutLoader.gone()
    }

    override fun onFailedGetLastMatch(message: String) {
        rvLastMatch.gone()
        layoutError.visible()
        tvError.text = message
    }

    override fun onSuccessGetLastMatch(lastMatches: List<LastMatch>?) {
        if (lastMatches != null) {
            rvLastMatch.visible()
            layoutError.gone()

            this.lastMatches.clear()
            this.lastMatches.addAll(lastMatches as MutableList)
            lastMatchAdapter.notifyDataSetChanged()
        }
        else {
            onFailedGetLastMatch(requireContext().getString(R.string.empty_last_match))
        }
    }

    private fun refreshLastMatch() {
        showLoader()

        presenter.getLastMatch(requireContext(), leagueId)
    }
}
