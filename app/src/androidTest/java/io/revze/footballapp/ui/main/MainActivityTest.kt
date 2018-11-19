package io.revze.footballapp.ui.main

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import io.revze.footballapp.R
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testFavoriteMatch() {
//        Aplikasi terbuka dan menampilkan beberapa view.
//        Memastikan bahwa terdapat sebuah bottom navigation yang ditampilkan.
//        Memastikan bahwa terdapat sebuah spinner liga pada tab next match yang ditampilkan.
//        Memberi jeda 3 detik untuk menunggu response next match.
//        Memastikan bahwa terdapat sebuah recyclerview next match yang ditampilkan.
//        Memberi tindakan scroll sampai item ke-5 pada recyclerview next match.
//        Memberi tindakan klik item ke-5 pada recyclerview next match.
//        Memberi jeda 3 detik untuk menunggu response match detail.
//        Memastikan tombol Favorite telah ditampilkan.
//        Memberi tindakan klik pada tombol Favorite.
//        Memastikan teks “Added to favorite match” telah ditampilkan.
//        Menekan tombol kembali.
//        Memastikan bahwa terdapat sebuah tab Team bottom navigation yang ditampilkan.
//        Memberi tindakan klik tab Team pada bottom navigation.
//        Memastikan bahwa terdapat sebuah spinner liga pada tab favorite yang ditampilkan.
//        Memberi jeda 3 detik untuk menunggu response team.
//        Memastikan bahwa terdapat sebuah recyclerview tim yang ditampilkan.
//        Memberi tindakan scroll sampai item ke-5 pada recyclerview team.
//        Memberi tindakan klik item ke-5 pada recyclerview team.
//        Memastikan tombol Favorite telah ditampilkan.
//        Memberi tindakan klik pada tombol Favorite.
//        Memastikan teks “Added to favorite team” telah ditampilkan.
//        Menekan tombol kembali.
//        Memastikan bahwa terdapat sebuah tab Favorite bottom navigation yang ditampilkan.
//        Memberi tindakan klik tab Favorite pada bottom navigation.
//        Memastikan bahwa terdapat sebuah tab layout pada tab Favorite.
//        Memastikan bahwa terdapat sebuah tab Team pada tab layout.
//        Memberi tindakan klik tab team pada tab Favorite.

        onView(withId(R.id.navigation_bottom)).check(matches(isDisplayed()))
        onView(withId(R.id.spinner_league_next_match)).check(matches(isDisplayed()))
        delay(3)

        onView(withId(R.id.rv_next_match)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_next_match)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
        onView(withId(R.id.rv_next_match)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))

        delay(3)
        onView(withId(R.id.menu_add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_add_to_favorite)).perform(click())
        onView(withText(R.string.success_added_favorite_match)).check(matches(isDisplayed()))
        pressBack()

        onView(withId(R.id.navigation_team)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_team)).perform(click())
        onView(withId(R.id.spinner_league)).check(matches(isDisplayed()))

        delay(3)
        onView(withId(R.id.rv_team)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_team)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
        onView(withId(R.id.rv_team)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))

        onView(withId(R.id.menu_add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_add_to_favorite)).perform(click())
        onView(withText(R.string.success_added_favorite_team)).check(matches(isDisplayed()))
        pressBack()

        onView(withId(R.id.navigation_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withId(R.id.favorite_tab)).check(matches(isDisplayed()))
        val favoriteTeamMatchers = Matchers.allOf(withText(R.string.favorite_team_title), isDescendantOfA(withId(R.id.favorite_tab)))
        onView(favoriteTeamMatchers).check(matches(isDisplayed()))
        delay(1)
        onView(favoriteTeamMatchers).perform(click())
        delay(1)
    }

    private fun delay(seconds: Long) {
        Thread.sleep(seconds * 1000)
    }
}