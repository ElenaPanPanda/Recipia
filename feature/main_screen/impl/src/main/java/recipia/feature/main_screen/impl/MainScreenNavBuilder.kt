package recipia.feature.main_screen.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import recipia.feature.main_screen.api.MainScreenRoutingContract

fun NavGraphBuilder.mainScreen() {
    composable<MainScreenRoutingContract.MainScreen> {
        MainScreen()
    }
}