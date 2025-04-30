package recipia.feature.recipe_list_impl.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun RecipeListScreen() {
    Scaffold { contentPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Hi, darling",
                modifier = Modifier
                    .padding(contentPadding)
                    .align(Alignment.Center)
            )
        }
    }
}