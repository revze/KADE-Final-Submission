package io.revze.footballapp.ui.favorite.team


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
import io.revze.footballapp.R
import io.revze.footballapp.adapter.FavoriteTeamAdapter
import io.revze.footballapp.db.ObjectBox
import io.revze.footballapp.model.FavoriteTeam
import io.revze.footballapp.utils.gone
import io.revze.footballapp.utils.visible
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import kotlinx.android.synthetic.main.layout_error.*

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteTeamFragment : Fragment() {


    private var favoriteTeams = mutableListOf<FavoriteTeam>()
    private lateinit var rvFavoriteTeam: RecyclerView
    private lateinit var layoutError: LinearLayout
    private lateinit var tvError: TextView
    private lateinit var btnRefresh: Button
    private lateinit var favoriteTeamBox: Box<FavoriteTeam>
    private lateinit var favoriteTeamAdapter: FavoriteTeamAdapter
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_team, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteTeamBox = ObjectBox.boxStore.boxFor(FavoriteTeam::class.java)
        rvFavoriteTeam = rv_favorite_team
        layoutError = layout_error
        tvError = tv_error
        btnRefresh = btn_refresh
        rvFavoriteTeam.layoutManager = LinearLayoutManager(context)
        favoriteTeamAdapter = FavoriteTeamAdapter(requireContext(), favoriteTeams)
        rvFavoriteTeam.adapter = favoriteTeamAdapter

        loadFavoriteTeam()
    }

    override fun onResume() {
        super.onResume()

        loadFavoriteTeam()
    }

    private fun loadFavoriteTeam() {
        favoriteTeams.clear()
        favoriteTeams.addAll(favoriteTeamBox.query().build().find())
        favoriteTeamAdapter.notifyDataSetChanged()

        btnRefresh.gone()
        if (favoriteTeams.size > 0) {
            rvFavoriteTeam.visible()
            layoutError.gone()
        } else {
            rvFavoriteTeam.gone()
            layoutError.visible()
            tvError.text = requireContext().getString(R.string.empty_favorite_team)
        }
    }
}
