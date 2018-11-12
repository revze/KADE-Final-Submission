package io.revze.footballapp.ui.match


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.revze.footballapp.adapter.CustomFragmentPagerAdapter

import io.revze.footballapp.R
import io.revze.footballapp.ui.match.last.LastMatchFragment
import io.revze.footballapp.ui.match.next.NextMatchFragment
import kotlinx.android.synthetic.main.fragment_match.*

/**
 * A simple [Fragment] subclass.
 *
 */
class MatchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = match_tab
        val viewPager = view_pager

        val viewPagerAdapter = CustomFragmentPagerAdapter(childFragmentManager)
        viewPager.adapter = viewPagerAdapter
        viewPagerAdapter.addFragment(NextMatchFragment(), requireContext().getString(R.string.next_match_title))
        viewPagerAdapter.addFragment(LastMatchFragment(), requireContext().getString(R.string.last_match_title))
        viewPagerAdapter.notifyDataSetChanged()
        tabLayout.setupWithViewPager(viewPager, false)
    }


}
