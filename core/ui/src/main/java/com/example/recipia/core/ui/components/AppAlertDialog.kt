package com.example.recipia.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.DarkBlue
import com.example.recipia.core.ui.theme.DarkRed
import com.example.recipia.core.ui.theme.MediumTeal
import com.example.recipia.core.ui.theme.TextMuted
import com.example.recipia.core.ui.theme.snowWhite

@Composable
fun AppAlertDialog(
    title: String,
    confirmButtonText: String,
    onConfirmButtonClick: () -> Unit,
    onShowAlertDialog: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null,
    dismissButtonText: String? = null,
    onDismissButtonClick: (() -> Unit)? = null,
    allowDismiss: Boolean = true,
) {
    Dialog(
        onDismissRequest = {
            if (allowDismiss) {
                onShowAlertDialog(false)
            }
        },
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(snowWhite)
                .padding(24.dp)
        ) {
            Text(
                text = title,
                style = AppTypography().playDisplayBold.copy(fontSize = 22.sp),
                color = DarkBlue,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )
            if (text != null) {
                Text(
                    text = text,
                    style = AppTypography().playDisplayBold.copy(fontSize = 15.sp),
                    color = TextMuted,
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .align(Alignment.End),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                if (dismissButtonText != null && onDismissButtonClick != null) {
                    IconTextButton(
                        text = dismissButtonText,
                        icon = null,
                        onClick = onDismissButtonClick,
                        contentColor = MediumTeal,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
                AppFilledButton(
                    text = confirmButtonText,
                    onClick = onConfirmButtonClick,
                    contentColor = DarkRed
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppAlertDialogPreview() {
    AppAlertDialog(
        title = "Do you want to exit?",
        onShowAlertDialog = { },
        confirmButtonText = "Exit",
        onConfirmButtonClick = {},
        dismissButtonText = "Cancel",
        onDismissButtonClick = { },
    )
}