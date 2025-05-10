package recipia.feature.recipe_list_impl.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipia.core.ui.theme.dividerColor

@Composable
fun AppHorizontalDivider(
    modifier: Modifier = Modifier
) {
    HorizontalDivider(
        modifier = modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = dividerColor
    )
}

@Preview(showBackground = true)
@Composable
private fun AppHorizontalDividerPreview() {
    AppHorizontalDivider(
        modifier = Modifier.padding(
            horizontal = 12.dp,
            vertical = 80.dp
        )
    )
}