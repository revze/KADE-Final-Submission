package io.revze.footballapp.ui.match.detail

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import io.objectbox.Box
import io.objectbox.BoxStore
import io.objectbox.kotlin.query
import io.objectbox.query.Query
import io.revze.footballapp.App
import io.revze.footballapp.utils.Helper
import io.revze.footballapp.R
import io.revze.footballapp.db.ObjectBox
import io.revze.footballapp.model.FavoriteMatch
import io.revze.footballapp.model.FavoriteMatch_
import io.revze.footballapp.model.MatchDetail
import io.revze.footballapp.utils.GlideApp
import io.revze.footballapp.utils.gone
import io.revze.footballapp.utils.visible
import kotlinx.android.synthetic.main.activity_match_detail.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.layout_loader.*

class MatchDetailActivity : AppCompatActivity(), MatchDetailView {

    companion object {
        const val MATCH_ID = "match_id"
    }

    private lateinit var presenter: MatchDetailPresenter
    private lateinit var id: String
    private lateinit var svMatchDetail: ScrollView
    private lateinit var layoutLoader: LinearLayout
    private lateinit var layoutError: LinearLayout
    private lateinit var tvError: TextView
    private lateinit var ctx: Context
    private lateinit var addToFavoriteMenu: MenuItem
    private lateinit var favoriteMatchBox: Box<FavoriteMatch>
    private lateinit var favoriteMatchQuery: Query<FavoriteMatch>
    private lateinit var matchDetail: MatchDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        presenter = MatchDetailPresenter(this)
        ctx = this
        favoriteMatchBox = ObjectBox.boxStore.boxFor(FavoriteMatch::class.java)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent != null) {
            id = intent.getStringExtra(MATCH_ID)
            favoriteMatchQuery = favoriteMatchBox.query {
                equal(FavoriteMatch_.eventId, id)
            }
        }

        svMatchDetail = sv_match_detail
        layoutLoader = layout_loader
        layoutError = layout_error
        tvError = tv_error

        presenter.getMatchDetail(this, id)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.menu_add_to_favorite -> {
                if (favoriteMatchQuery.findFirst() != null) {
                    favoriteMatchQuery.remove()
                    addToFavoriteMenu.icon = ContextCompat.getDrawable(ctx, R.drawable.ic_add_to_favorites)
                } else {
                    favoriteMatchBox.put(FavoriteMatch(eventId = id,
                            homeTeam = matchDetail.homeTeam,
                            awayTeam = matchDetail.awayTeam,
                            dateEvent = matchDetail.dateEvent,
                            timeEvent = matchDetail.timeEvent,
                            homeScore = matchDetail.homeScore,
                            awayScore = matchDetail.awayScore))
                    addToFavoriteMenu.icon = ContextCompat.getDrawable(ctx, R.drawable.ic_added_to_favorites)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoader() {
        svMatchDetail.gone()
        layoutLoader.visible()
        layoutError.gone()
    }

    override fun hideLoader() {
        layoutLoader.gone()
    }

    override fun onSuccessGetMatchDetail(matchDetail: MatchDetail?) {
        addToFavoriteMenu.isVisible = true
        svMatchDetail.visible()
        layoutError.gone()

        if (matchDetail != null) {
            this.matchDetail = matchDetail
            val helper = Helper("${matchDetail.dateEvent} ${matchDetail.timeEvent}")

            presenter.getTeamDetail(ctx, matchDetail.homeTeamId, "home")
            presenter.getTeamDetail(ctx, matchDetail.awayTeamId, "away")
            tv_date.text = helper.convertDate()
            tv_time.text = helper.convertTime()
            tv_home_team.text = matchDetail.homeTeam
            tv_away_team.text = matchDetail.awayTeam
            tv_home_score.text = matchDetail.homeScore
            tv_away_score.text = matchDetail.awayScore
            tv_home_goal_details.text = matchDetail.homeGoalDetails
            tv_away_goal_details.text = matchDetail.awayGoalDetails
            tv_home_shots.text = matchDetail.homeShots
            tv_away_shots.text = matchDetail.awayShots
            tv_home_goal_keeper.text = matchDetail.homeLineupGoalkeeper
            tv_away_goal_keeper.text = matchDetail.awayLineupGoalkeeper
            tv_home_defender.text = matchDetail.homeLineupDefense
            tv_away_defender.text = matchDetail.awayLineupDefense
            tv_home_midfield.text = matchDetail.homeLineupMidfield
            tv_away_midfield.text = matchDetail.awayLineupMidfield
            tv_home_forward.text = matchDetail.homeLineupForward
            tv_away_forward.text = matchDetail.awayLineupForward
            tv_home_substitutes.text = matchDetail.homeLineupSubstitutes
            tv_away_substitutes.text = matchDetail.awayLineupSubstitutes
        }

    }

    override fun onFailedGetMatchDetail(message: String) {
        svMatchDetail.gone()
        layoutError.visible()
        tvError.text = message
    }

    override fun onDetachView() {
        presenter.onDetach()
    }

    override fun onDestroy() {
        onDetachView()
        super.onDestroy()
    }

    override fun showHomeTeamLogo(logo: String?) {
        GlideApp.with(ctx).load(logo).into(iv_home_team)
    }

    override fun showAwayTeamLogo(logo: String?) {
        GlideApp.with(ctx).load(logo).into(iv_away_team)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        addToFavoriteMenu = menu.getItem(0)
        addToFavoriteMenu.isVisible = false
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    private fun setFavorite() {
        if (favoriteMatchQuery.findFirst() != null) addToFavoriteMenu.icon = ContextCompat.getDrawable(ctx, R.drawable.ic_added_to_favorites)
        else addToFavoriteMenu.icon = ContextCompat.getDrawable(ctx, R.drawable.ic_add_to_favorites)
    }
}
