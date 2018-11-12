package io.revze.footballapp.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import io.revze.footballapp.R
import io.revze.footballapp.ui.favorite.FavoriteFragment
import io.revze.footballapp.ui.match.MatchFragment
import io.revze.footballapp.ui.match.search.SearchMatchActivity
import io.revze.footballapp.ui.team.list.TeamFragment
import io.revze.footballapp.ui.team.search.SearchTeamActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var menuSearch: MenuItem
    private var activeFragment = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = navigation_bottom
        bottomNavigation.setOnNavigationItemSelectedListener(this)
        bottomNavigation.selectedItemId = R.id.navigation_match
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.navigation_match -> {
                val fragmentName = MatchFragment().javaClass.simpleName
                activeFragment = fragmentName

                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, MatchFragment(), fragmentName)
                        .commitAllowingStateLoss()

                if (this::menuSearch.isInitialized) menuSearch.isVisible = true

                true
            }
            R.id.navigation_team -> {
                val fragmentName = TeamFragment().javaClass.simpleName
                activeFragment = fragmentName

                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, TeamFragment(), fragmentName)
                        .commitAllowingStateLoss()

                if (this::menuSearch.isInitialized) menuSearch.isVisible = true

                true
            }
            R.id.navigation_favorite -> {
                val fragmentName = FavoriteFragment().javaClass.simpleName
                activeFragment = fragmentName

                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, FavoriteFragment(), fragmentName)
                        .commitAllowingStateLoss()

                if (this::menuSearch.isInitialized) menuSearch.isVisible = false

                true
            }
            else -> false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menuSearch = menu.findItem(R.id.menu_search)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.menu_search -> {
                when (activeFragment) {
                    MatchFragment().javaClass.simpleName -> startActivity<SearchMatchActivity>()
                    TeamFragment().javaClass.simpleName -> startActivity<SearchTeamActivity>()
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
