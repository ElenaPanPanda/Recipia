package com.example.recipia.feature.calendar.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.recipia.feature.calendar.api.CalendarRoutingContract
import com.example.recipia.feature.calendar.impl.ui.CalendarScreen

fun NavGraphBuilder.calendarScreen() {
    composable<CalendarRoutingContract.Calendar> {
        CalendarScreen()
    }
}