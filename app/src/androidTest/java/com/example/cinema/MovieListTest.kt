package com.example.cinema

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieListTest {

    @Test
    fun testMovieListDisplayed() {
        // Запускаем MainActivity
        ActivityScenario.launch(MainActivity::class.java)

        // Предполагается, что у вас есть элемент RecyclerView с id movieRecyclerView
        onView(withId(R.id.recyclerViewMovies))
            .check(matches(isDisplayed()))
    }
}

