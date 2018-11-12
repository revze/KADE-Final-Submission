package io.revze.footballapp.ui.team.detail.description


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import io.revze.footballapp.R
import kotlinx.android.synthetic.main.fragment_team_description.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamDescriptionFragment : Fragment() {

    companion object {
        const val DESCRIPTION = "description"
        fun getInstance(description: String): TeamDescriptionFragment {
            val bundle = Bundle()
            bundle.putString(DESCRIPTION, description)
            val fragment = TeamDescriptionFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
    private var description: String? = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_description, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        description = arguments?.getString(DESCRIPTION) ?: ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_overview.text = description
    }
}
