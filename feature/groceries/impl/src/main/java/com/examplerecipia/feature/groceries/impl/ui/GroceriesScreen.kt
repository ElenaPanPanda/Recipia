package com.examplerecipia.feature.groceries.impl.ui

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.recipia.core.ui.components.ErrorScreen
import com.example.recipia.core.ui.components.LoadingScreen
import com.examplerecipia.feature.groceries.impl.ui.components.GroceriesContent
import androidx.compose.ui.platform.LocalContext

@Composable
fun GroceriesScreen(
    viewModel: GroceriesViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val event: (GroceriesEvent) -> Unit = viewModel::obtainEvent

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is GroceriesEffect.ShowSnackBar -> {}
                is GroceriesEffect.ShareList -> {
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, effect.text)
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(sendIntent, null)
                    context.startActivity(shareIntent)
                }
            }
        }
    }

    when (state) {
        is GroceriesState.Loading -> LoadingScreen()
        is GroceriesState.Error -> ErrorScreen((state as GroceriesState.Error).message)
        is GroceriesState.Success -> GroceriesContent(
            state = state as GroceriesState.Success,
            event = event,
            onShareClicked = { event(GroceriesEvent.OnShareList) },
        )
    }
}