package io.revze.footballapp.ui.team.detail

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import io.revze.footballapp.R
import io.revze.footballapp.adapter.CustomFragmentPagerAdapter
import io.revze.footballapp.ui.team.detail.description.TeamDescriptionFragment
import io.revze.footballapp.ui.team.detail.player.TeamPlayerFragment
import io.revze.footballapp.utils.GlideApp
import kotlinx.android.synthetic.main.activity_team_detail.*

class TeamDetailActivity : AppCompatActivity() {

    companion object {
        const val TEAM_ID = "team_id"
        const val LOGO = "logo"
        const val TEAM_NAME = "team_name"
        const val FORMED_YEAR = "formed_year"
        const val STADIUM = "stadium"
        const val DESCRIPTION = "description"
    }
    private lateinit var addToFavoriteMenu: MenuItem
    private lateinit var context: Context
    private var teamId = ""
    private var description = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        context = this
        val toolbar = toolbar
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowTitleEnabled(false)

        if (intent != null) {
            teamId = intent.getStringExtra(TEAM_ID)
            GlideApp.with(this).load(intent.getStringExtra(LOGO)).into(iv_logo)
            tv_team_name.text = intent.getStringExtra(TEAM_NAME)
            tv_formed_year.text = intent.getStringExtra(FORMED_YEAR)
            tv_stadium.text = intent.getStringExtra(STADIUM)
            description = intent.getStringExtra(DESCRIPTION)
        }


        val tabLayout = team_tab
        val viewPager = view_pager

        val viewPagerAdapter = CustomFragmentPagerAdapter(supportFragmentManager)
        viewPager.adapter = viewPagerAdapter
        viewPagerAdapter.addFragment(TeamDescriptionFragment.getInstance(description), getString(R.string.team_overview_title))
        viewPagerAdapter.addFragment(TeamPlayerFragment.getInstance(teamId), getString(R.string.team_player_title))
        viewPagerAdapter.notifyDataSetChanged()
        tabLayout.setupWithViewPager(viewPager, false)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.menu_add_to_favorite -> {
                addToFavoriteMenu.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        addToFavoriteMenu = menu.getItem(0)
        return super.onCreateOptionsMenu(menu)
    }
}
