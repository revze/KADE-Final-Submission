package io.revze.footballapp.ui.team.detail

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import io.objectbox.Box
import io.objectbox.kotlin.query
import io.objectbox.query.Query
import io.revze.footballapp.R
import io.revze.footballapp.adapter.CustomFragmentPagerAdapter
import io.revze.footballapp.db.ObjectBox
import io.revze.footballapp.model.FavoriteTeam
import io.revze.footballapp.model.FavoriteTeam_
import io.revze.footballapp.ui.team.detail.description.TeamDescriptionFragment
import io.revze.footballapp.ui.team.detail.player.list.TeamPlayerFragment
import io.revze.footballapp.utils.GlideApp
import kotlinx.android.synthetic.main.activity_team_detail.*
import okhttp3.internal.Util.equal
import org.jetbrains.anko.contentView
import org.jetbrains.anko.design.snackbar

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
    private var logo = ""
    private var teamName = ""
    private var formedYear = ""
    private var stadium = ""
    private var description = ""
    private lateinit var favoriteTeamBox: Box<FavoriteTeam>
    private lateinit var favoriteTeamQuery: Query<FavoriteTeam>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        context = this
        favoriteTeamBox = ObjectBox.boxStore.boxFor(FavoriteTeam::class.java)
        val toolbar = toolbar
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowTitleEnabled(false)

        if (intent != null) {
            teamId = intent.getStringExtra(TEAM_ID)
            logo = intent.getStringExtra(LOGO)
            teamName = intent.getStringExtra(TEAM_NAME)
            formedYear = intent.getStringExtra(FORMED_YEAR)
            stadium = intent.getStringExtra(STADIUM)
            description = intent.getStringExtra(DESCRIPTION) ?: ""

            favoriteTeamQuery = favoriteTeamBox.query {
                equal(FavoriteTeam_.id, teamId)
            }
            GlideApp.with(this).load(logo).into(iv_logo)
            tv_team_name.text = teamName
            tv_formed_year.text = formedYear
            tv_stadium.text = stadium
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
                if (favoriteTeamQuery.findFirst() != null) {
                    favoriteTeamQuery.remove()
                    addToFavoriteMenu.icon = ContextCompat.getDrawable(context, R.drawable.ic_add_to_favorites)
                    contentView?.snackbar(getString(R.string.success_removed_favorite_team))
                } else {
                    favoriteTeamBox.put(FavoriteTeam(id = teamId,
                            name = teamName,
                            description = description,
                            formedYear = formedYear,
                            stadium = stadium,
                            logo = logo))
                    addToFavoriteMenu.icon = ContextCompat.getDrawable(context, R.drawable.ic_added_to_favorites)
                    contentView?.snackbar(getString(R.string.success_added_favorite_team))
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        addToFavoriteMenu = menu.getItem(0)
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    private fun setFavorite() {
        if (favoriteTeamQuery.findFirst() != null) addToFavoriteMenu.icon = ContextCompat.getDrawable(context, R.drawable.ic_added_to_favorites)
        else addToFavoriteMenu.icon = ContextCompat.getDrawable(context, R.drawable.ic_add_to_favorites)
    }
}
