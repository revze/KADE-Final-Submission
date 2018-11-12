package io.revze.footballapp.ui.team.search

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import io.revze.footballapp.R
import io.revze.footballapp.adapter.TeamAdapter
import io.revze.footballapp.model.Team
import io.revze.footballapp.utils.gone
import io.revze.footballapp.utils.visible
import kotlinx.android.synthetic.main.activity_search_team.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.layout_loader.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.onEditorAction

class SearchTeamActivity : AppCompatActivity(), SearchTeamView {

    private lateinit var presenter: SearchTeamPresenter
    private lateinit var etSearch: EditText
    private val teams = mutableListOf<Team>()
    private lateinit var teamAdapter: TeamAdapter
    private lateinit var rvTeam: RecyclerView
    private lateinit var layoutLoader: LinearLayout
    private lateinit var layoutError: LinearLayout
    private lateinit var tvError: TextView
    private lateinit var btnRefresh: Button
    private lateinit var ctx: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)

        presenter = SearchTeamPresenter(this)
        ctx = this
        val toolbar = toolbar
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowTitleEnabled(false)

        etSearch = et_search
        rvTeam = rv_team
        layoutLoader = layout_loader
        layoutError = layout_error
        tvError = tv_error
        btnRefresh = btn_refresh
        teamAdapter = TeamAdapter(this, teams)
        rvTeam.layoutManager = LinearLayoutManager(this)
        rvTeam.adapter = teamAdapter

        etSearch.onEditorAction { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val search = etSearch.text.toString().trim()
                if (!search.equals("")) presenter.searchTeam(ctx, etSearch.text.toString().trim())
                else longToast(getString(R.string.empty_search_form_error))
            }
        }
        btnRefresh.onClick { refreshSearch() }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.menu_clear -> {
                etSearch.setText("")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_clear, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun showLoader() {
        layoutLoader.visible()
        layoutError.gone()
        rvTeam.gone()
        btnRefresh.visible()
    }

    override fun hideLoader() {
        layoutLoader.gone()
    }

    override fun onFailedSearchTeam(message: String) {
        rvTeam.gone()
        layoutError.visible()
        tvError.text = message
    }

    override fun onSuccessSearchTeam(teams: List<Team>?) {
        if (teams != null) {
            rvTeam.visible()
            layoutError.gone()

            this.teams.clear()
            this.teams.addAll(teams as MutableList)
            teamAdapter.notifyDataSetChanged()
        }
        else {
            btnRefresh.gone()
            onFailedSearchTeam(getString(R.string.empty_search_team))
        }
    }


    override fun onDetachView() {
        presenter.onDetach()
    }

    override fun onDestroy() {
        onDetachView()
        super.onDestroy()
    }

    private fun refreshSearch() {
        showLoader()

        presenter.searchTeam(ctx, etSearch.text.toString().trim())
    }
}
