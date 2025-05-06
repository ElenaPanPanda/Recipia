package recipia.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import recipia.core.ui.R
import recipia.core.ui.icons.Icons
import recipia.core.ui.theme.likeBackground
import recipia.core.ui.theme.likeYellow
import recipia.core.ui.theme.softBlack

@Composable
fun Like(
    isLiked: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .background(likeBackground),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector =
            if (isLiked) ImageVector.vectorResource(id = Icons.like)
            else ImageVector.vectorResource(id = Icons.emptyLike),
            contentDescription =
            if (isLiked) stringResource(R.string.core_ui_liked)
            else stringResource(R.string.core_ui_not_liked),
            modifier = Modifier
                .padding(6.dp)
                .size(20.dp),
            tint = if (isLiked) likeYellow else softBlack
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LikePreview() {
    Row(modifier = Modifier.padding(8.dp)) {
        Like(isLiked = true)
        Spacer(modifier = Modifier.width(4.dp))
        Like(isLiked = false)
    }
}