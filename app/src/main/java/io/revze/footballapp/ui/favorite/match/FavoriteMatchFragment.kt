package io.revze.footballapp.ui.favorite.match


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import io.objectbox.Box
import io.objectbox.query.Query

import io.revze.footballapp.R
import io.revze.footballapp.adapter.FavoriteMatchAdapter
import io.revze.footballapp.db.ObjectBox
import io.revze.footballapp.model.FavoriteMatch
import io.revze.footballapp.utils.gone
import io.revze.footballapp.utils.visible
import kotlinx.android.synthetic.main.fragment_favorite_match.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.layout_loader.*

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteMatchFragment : Fragment() {

    private var favoriteMatches = mutableListOf<FavoriteMatch>()
    private lateinit var rvFavoriteMatch: RecyclerView
    private lateinit var layoutError: LinearLayout
    private lateinit var tvError: TextView
    private lateinit var btnRefresh: Button
    private lateinit var favoriteMatchBox: Box<FavoriteMatch>
    private lateinit var favoriteMatchAdapter: FavoriteMatchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteMatchBox = ObjectBox.boxStore.boxFor(FavoriteMatch::class.java)
        rvFavoriteMatch = rv_favorite_match
        layoutError = layout_error
        tvError = tv_error
        btnRefresh = btn_refresh
        rvFavoriteMatch.layoutManager = LinearLayoutManager(context)
        favoriteMatchAdapter = FavoriteMatchAdapter(requireContext(), favoriteMatches)
        rvFavoriteMatch.adapter = favoriteMatchAdapter

        loadFavoriteMatch()
    }

    override fun onResume() {
        super.onResume()

        loadFavoriteMatch()
    }

    private fun loadFavoriteMatch() {
        favoriteMatches.clear()
        favoriteMatches.addAll(favoriteMatchBox.query().build().find())
        favoriteMatchAdapter.notifyDataSetChanged()

        btnRefresh.gone()
        if (favoriteMatches.size > 0) {
            rvFavoriteMatch.visible()
            layoutError.gone()
        } else {
            rvFavoriteMatch.gone()
            layoutError.visible()
            tvError.text = requireContext().getString(R.string.empty_favorite_match)
        }
    }
}
