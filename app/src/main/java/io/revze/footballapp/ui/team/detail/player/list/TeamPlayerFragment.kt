package io.revze.footballapp.ui.team.detail.player.list


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

import io.revze.footballapp.R
import io.revze.footballapp.adapter.PlayerAdapter
import io.revze.footballapp.model.Player
import io.revze.footballapp.utils.gone
import io.revze.footballapp.utils.visible
import kotlinx.android.synthetic.main.fragment_team_player.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.layout_loader.*
import org.jetbrains.anko.sdk27.coroutines.onClick

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamPlayerFragment : Fragment(), TeamPlayerView {

    companion object {
        const val TEAM_ID = "team_id"
        fun getInstance(teamId: String): TeamPlayerFragment {
            val bundle = Bundle()
            bundle.putString(TEAM_ID, teamId)
            val fragment = TeamPlayerFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
    private lateinit var presenter: TeamPlayerPresenter
    private var teamId: String = ""
    private val players = mutableListOf<Player>()
    private lateinit var playerAdapter: PlayerAdapter
    private lateinit var rvPlayer: RecyclerView
    private lateinit var layoutLoader: LinearLayout
    private lateinit var layoutError: LinearLayout
    private lateinit var tvError: TextView
    private lateinit var btnRefresh: Button


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_player, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        teamId = arguments?.getString(TEAM_ID) ?: ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = TeamPlayerPresenter(this)
        rvPlayer = rv_player
        playerAdapter = PlayerAdapter(requireContext(), players)
        rvPlayer.layoutManager = LinearLayoutManager(context)
        rvPlayer.adapter = playerAdapter
        layoutLoader = layout_loader
        layoutError = layout_error
        tvError = tv_error
        btnRefresh = btn_refresh

        btnRefresh.onClick { refreshPlayer() }
        presenter.getTeam(requireContext(), teamId)
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
        rvPlayer.gone()
    }

    override fun hideLoader() {
        layoutLoader.gone()
    }

    override fun onSuccessGetPlayer(players: List<Player>?) {
        if (players != null) {
            rvPlayer.visible()
            layoutError.gone()

            this.players.clear()
            this.players.addAll(players as MutableList)
            playerAdapter.notifyDataSetChanged()
        } else {
            onFailedGetPlayer(requireContext().getString(R.string.empty_player))
        }
    }

    override fun onFailedGetPlayer(message: String) {
        rvPlayer.gone()
        layoutError.visible()
        tvError.text = message
    }

    private fun refreshPlayer() {
        presenter.getTeam(requireContext(), teamId)
    }
}
