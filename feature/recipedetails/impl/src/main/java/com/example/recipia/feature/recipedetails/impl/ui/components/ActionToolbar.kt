package com.example.recipia.feature.recipedetails.impl.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipia.core.ui.R as uiR
import com.example.recipia.core.ui.components.AppHorizontalDivider
import com.example.recipia.core.ui.icons.Icons
import com.example.recipia.core.ui.theme.DividerNavBarColor
import com.example.recipia.core.ui.theme.LighterBeige
import com.example.recipia.core.ui.theme.BurntOrange
import com.example.recipia.core.ui.theme.DarkTeal
import com.example.recipia.core.ui.theme.DarkRed
import com.example.recipia.core.ui.theme.GoldOrange
import com.example.recipia.core.ui.theme.MediumTeal

@Composable
fun ActionToolbar(
    onEditClicked: () -> Unit,
    onSaveClicked: () -> Unit,
    onCalendarClicked: () -> Unit,
    onShareClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 2.dp,
        color = LighterBeige.copy(alpha = 0.9f),
    ) {
        Column {
            AppHorizontalDivider(color = DividerNavBarColor)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Top
            ) {
                ActionToolBarButton(
                    iconRes = Icons.edit,
                    text = stringResource(id = uiR.string.core_ui_edit),
                    onClick = onEditClicked,
                    iconColor = DarkTeal
                )
                ActionToolBarButton(
                    iconRes = Icons.bookmark,
                    text = stringResource(id = uiR.string.core_ui_save),
                    onClick = onSaveClicked,
                    iconColor = MediumTeal
                )
                ActionToolBarButton(
                    iconRes = Icons.calendarAdd,
                    text = stringResource(id = uiR.string.core_ui_calendar),
                    onClick = onCalendarClicked,
                    iconColor = BurntOrange
                )
                ActionToolBarButton(
                    iconRes = Icons.share,
                    text = stringResource(id = uiR.string.core_ui_share),
                    onClick = onShareClicked,
                    iconColor = GoldOrange
                )
                ActionToolBarButton(
                    iconRes = Icons.trash,
                    text = stringResource(id = uiR.string.core_ui_delete),
                    onClick = onDeleteClicked,
                    iconColor = DarkRed
                )
            }
            AppHorizontalDivider(color = DividerNavBarColor)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ActionToolbarPreview() {
    ActionToolbar(
        onEditClicked = {},
        onSaveClicked = {},
        onCalendarClicked = {},
        onShareClicked = {},
        onDeleteClicked = {},
    )
}