package com.example.recipia.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.DarkBlue
import com.example.recipia.core.ui.theme.DarkTeal
import com.example.recipia.core.ui.theme.LightTeal
import com.example.recipia.core.ui.theme.MediumTeal
import com.example.recipia.core.ui.theme.TextMuted
import com.example.recipia.core.ui.theme.snowWhite

@Composable
fun AppInputField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
    title: String? = null,
    isMandatory: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    minLines: Int = 1
) {
    var isFocused by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        if (title != null) {
            Row(modifier = Modifier.padding(bottom = 10.dp)) {
                AppTitle(text = title)
                if (isMandatory) {
                    Text(
                        text = " *",
                        color = Color.Red,
                        style = AppTypography().playDisplayBold.copy(fontSize = 17.6.sp),
                    )
                }
            }
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = if (isFocused) MediumTeal else LightTeal,
                    shape = RoundedCornerShape(8.dp)
                )
                .shadow(
                    if (isFocused) 3.dp else 0.dp,
                    RoundedCornerShape(8.dp),
                    spotColor = MediumTeal.copy(alpha = 0.2f)
                )
                .background(snowWhite, RoundedCornerShape(8.dp))
                .onFocusChanged { focusState -> isFocused = focusState.isFocused },
            textStyle = AppTypography().poppinsNormal.copy(fontSize = 15.2.sp, color = DarkBlue),
            cursorBrush = SolidColor(DarkTeal),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            minLines = if (singleLine) 1 else minLines,
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = if (singleLine) Alignment.CenterStart else Alignment.TopStart,
                    modifier = Modifier.padding(16.dp)
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = hint,
                            style = AppTypography().poppinsNormal.copy(fontSize = 15.2.sp),
                            color = TextMuted.copy(alpha = 0.8f)
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppInputFieldPreview() {
    Column(modifier = Modifier.padding(12.dp)) {
        AppInputField(
            value = "",
            onValueChange = {},
            hint = "Hint",
            modifier = Modifier.padding(bottom = 8.dp)
        )
        AppInputField(
            value = "Input text",
            onValueChange = {},
            title = "Title",
            isMandatory = true,
            hint = "Hint",
        )
    }
}