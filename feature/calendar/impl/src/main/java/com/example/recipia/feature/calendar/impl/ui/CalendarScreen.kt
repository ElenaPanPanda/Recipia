package com.example.recipia.feature.calendar.impl.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CalendarScreen() {
    Scaffold { contentPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Here will be your calendar",
                modifier = Modifier
                    .padding(contentPadding)
                    .align(Alignment.Center)
            )
        }
    }
}