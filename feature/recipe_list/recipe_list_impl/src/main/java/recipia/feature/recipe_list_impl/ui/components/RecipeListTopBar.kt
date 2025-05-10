package recipia.feature.recipe_list_impl.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.core.ui.R as uiR
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.DarkTeal
import com.example.recipia.core.ui.theme.LighterBeige

@Composable
fun RecipeListTopBar(
    onSearchClick: () -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }

    Surface(
        modifier = modifier.height(64.dp),
        color = LighterBeige.copy(alpha = 0.8f),
        shadowElevation = 0.5.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = uiR.string.core_ui_app_name),
                style = AppTypography().playDisplayBold.copy(fontSize = 30.sp),
                color = DarkTeal,
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = ImageVector.vectorResource(id = Icons.search),
                contentDescription = stringResource(id = uiR.string.core_ui_search_recipes),
                tint = DarkTeal,
                modifier = Modifier
                    .clickable(
                        onClick = onSearchClick,
                        interactionSource = interactionSource,
                        indication = null
                    )
                    .padding(8.dp)
                    .size(20.dp)
            )

            Spacer(modifier = Modifier.width(6.dp))

            Icon(
                imageVector = ImageVector.vectorResource(id = Icons.plus),
                contentDescription = stringResource(id = uiR.string.core_ui_add_new_recipe),
                tint = DarkTeal,
                modifier = Modifier
                    .clickable(
                        onClick = onAddClick,
                        interactionSource = interactionSource,
                        indication = null
                    )
                    .padding(8.dp)
                    .size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecipeListTopBarPreview() {
    RecipeListTopBar(
        onSearchClick = {},
        onAddClick = {},
    )
}