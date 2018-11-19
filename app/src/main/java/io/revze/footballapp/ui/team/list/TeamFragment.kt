package io.revze.footballapp.ui.team.list


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import io.revze.footballapp.R
import io.revze.footballapp.adapter.TeamAdapter
import io.revze.footballapp.model.League
import io.revze.footballapp.model.Team
import io.revze.footballapp.utils.gone
import io.revze.footballapp.utils.visible
import kotlinx.android.synthetic.main.fragment_team.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.layout_loader.*
import org.jetbrains.anko.contentView
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.longToast
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamFragment : Fragment(), TeamView {

    private val leagues = mutableListOf<League>()
    private val leagueNames = mutableListOf<String>()
    private lateinit var presenter: TeamPresenter
    private lateinit var leagueAdapter: ArrayAdapter<String>
    private val teams = mutableListOf<Team>()
    private lateinit var teamAdapter: TeamAdapter
    private lateinit var rvTeam: RecyclerView
    private lateinit var layoutLoader: LinearLayout
    private lateinit var layoutError: LinearLayout
    private lateinit var tvError: TextView
    private lateinit var btnRefresh: Button
    private var leagueId = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = TeamPresenter(this)
        val spinnerLeague = spinner_league
        leagueAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, leagueNames)
        rvTeam = rv_team
        layoutLoader = layout_loader
        layoutError = layout_error
        tvError = tv_error
        btnRefresh = btn_refresh
        teamAdapter = TeamAdapter(requireContext(), teams)
        rv_team.layoutManager = LinearLayoutManager(context)
        rv_team.adapter = teamAdapter

        leagueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerLeague.adapter = leagueAdapter
        spinnerLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueId = leagues[position].id
                presenter.getTeam(requireContext(), leagues[position].id)
            }
        }

        presenter.getLeague(requireContext())
        btnRefresh.onClick { refreshTeam() }
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
        requireActivity().contentView?.snackbar(message)
    }


    override fun showLoader() {
        layoutLoader.visible()
        layoutError.gone()
        rvTeam.gone()
    }

    override fun hideLoader() {
        layoutLoader.gone()
    }

    override fun onSuccessGetTeam(teams: List<Team>?) {
        if (teams != null) {
            rvTeam.visible()
            layoutError.gone()

            this.teams.clear()
            this.teams.addAll(teams as MutableList)
            teamAdapter.notifyDataSetChanged()
        } else {
            onFailedGetTeam(requireContext().getString(R.string.empty_team))
        }
    }

    override fun onFailedGetTeam(message: String) {
        rvTeam.gone()
        layoutError.visible()
        tvError.text = message
    }

    private fun refreshTeam() {
        showLoader()

        presenter.getTeam(requireContext(), leagueId)
    }
}
