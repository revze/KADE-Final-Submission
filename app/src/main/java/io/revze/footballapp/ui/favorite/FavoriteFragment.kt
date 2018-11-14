package io.revze.footballapp.ui.favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import io.revze.footballapp.R
import io.revze.footballapp.adapter.CustomFragmentPagerAdapter
import io.revze.footballapp.ui.favorite.match.FavoriteMatchFragment
import io.revze.footballapp.ui.favorite.team.FavoriteTeamFragment
import kotlinx.android.synthetic.main.fragment_favorite.*

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = favorite_tab
        val viewPager = view_pager_favorite

        val viewPagerAdapter = CustomFragmentPagerAdapter(childFragmentManager)
        viewPager.adapter = viewPagerAdapter
        viewPagerAdapter.addFragment(FavoriteMatchFragment(), requireContext().getString(R.string.favorite_match_title))
        viewPagerAdapter.addFragment(FavoriteTeamFragment(), requireContext().getString(R.string.favorite_team_title))
        viewPagerAdapter.notifyDataSetChanged()
        tabLayout.setupWithViewPager(viewPager, false)
    }
}
