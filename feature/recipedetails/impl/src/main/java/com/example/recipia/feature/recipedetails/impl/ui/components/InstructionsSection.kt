package com.example.recipia.feature.recipedetails.impl.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.R as uiR
import com.example.recipia.core.ui.theme.AppTypography
import com.example.recipia.core.ui.theme.DarkBlue
import com.example.recipia.core.ui.theme.DarkTeal

@Composable
fun InstructionsSection(
    instructions: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = uiR.string.core_ui_instructions),
            style = AppTypography().poppinsSemiBold.copy(fontSize = 18.sp),
            color = DarkTeal,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = instructions,
            style = AppTypography().poppinsNormal.copy(
                fontSize = 15.sp,
                lineHeight = 25.sp
            ),
            color = DarkBlue
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun InstructionsSectionPreview() {
    InstructionsSection(
        instructions = "1. Sauté Aromatics: Heat olive oil in a large pot or Dutch oven over medium heat. Add onion and cook until softened, about 5-7 minutes. Stir in garlic and ginger; cook for another minute until fragrant.\n" +
                "\n" +
                "5. Serve: Ladle the stew into bowls. Garnish with fresh cilantro, a dollop of yogurt, or a squeeze of lime juice, if desired. Serve hot with naan bread or rice."
    )
}