package io.revze.footballapp.ui.team.detail.player.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import io.revze.footballapp.R
import io.revze.footballapp.utils.GlideApp
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : AppCompatActivity() {

    companion object {
        const val NAME = "name"
        const val PHOTO = "photo"
        const val HEIGHT = "height"
        const val WEIGHT = "weight"
        const val POSITION = "position"
        const val DESCRIPTION = "description"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent != null) {
            supportActionBar?.title = intent.getStringExtra(NAME)
            GlideApp.with(this).load(intent.getStringExtra(PHOTO)).into(iv_player)
            tv_weight.text = intent.getStringExtra(WEIGHT)
            tv_height.text = intent.getStringExtra(HEIGHT)
            tv_position.text = intent.getStringExtra(POSITION)
            tv_description.text = intent.getStringExtra(DESCRIPTION)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
