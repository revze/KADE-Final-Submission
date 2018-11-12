package io.revze.footballapp.ui.match.search

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
import io.revze.footballapp.adapter.SearchMatchAdapter
import io.revze.footballapp.model.SearchMatch
import io.revze.footballapp.utils.gone
import io.revze.footballapp.utils.visible
import kotlinx.android.synthetic.main.activity_search_match.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.layout_loader.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.onEditorAction

class SearchMatchActivity : AppCompatActivity(), SearchMatchView {

    private lateinit var presenter: SearchMatchPresenter
    private lateinit var etSearch: EditText
    private val searchMatches = mutableListOf<SearchMatch>()
    private lateinit var rvMatch: RecyclerView
    private lateinit var searchMatchAdapter: SearchMatchAdapter
    private lateinit var layoutLoader: LinearLayout
    private lateinit var layoutError: LinearLayout
    private lateinit var tvError: TextView
    private lateinit var btnRefresh: Button
    private lateinit var ctx: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)

        presenter = SearchMatchPresenter(this)
        ctx = this
        val toolbar = toolbar
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowTitleEnabled(false)

        etSearch = et_search
        rvMatch = rv_match
        layoutLoader = layout_loader
        layoutError = layout_error
        tvError = tv_error
        btnRefresh = btn_refresh
        searchMatchAdapter = SearchMatchAdapter(this, searchMatches)
        rvMatch.layoutManager = LinearLayoutManager(this)
        rvMatch.adapter = searchMatchAdapter

        etSearch.onEditorAction { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val search = etSearch.text.toString().trim()
                if (!search.equals("")) presenter.searchMatch(ctx, etSearch.text.toString().trim())
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

    override fun onDetachView() {
        presenter.onDetach()
    }

    override fun onDestroy() {
        onDetachView()
        super.onDestroy()
    }

    override fun showLoader() {
        layoutLoader.visible()
        layoutError.gone()
        rvMatch.gone()
        btnRefresh.visible()
    }

    override fun hideLoader() {
        layoutLoader.gone()
    }

    override fun onFailedSearchMatch(message: String) {
        rvMatch.gone()
        layoutError.visible()
        tvError.text = message
    }

    override fun onSuccessSearchMatch(matches: List<SearchMatch>?) {
        if (matches != null) {
            rvMatch.visible()
            layoutError.gone()

            searchMatches.clear()
            searchMatches.addAll(matches as MutableList)
            searchMatchAdapter.notifyDataSetChanged()
        }
        else {
            btnRefresh.gone()
            onFailedSearchMatch(getString(R.string.empty_search_match))
        }
    }

    private fun refreshSearch() {
        showLoader()

        presenter.searchMatch(ctx, etSearch.text.toString().trim())
    }
}
