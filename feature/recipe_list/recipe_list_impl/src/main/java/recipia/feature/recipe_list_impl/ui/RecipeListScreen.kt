package recipia.feature.recipe_list_impl.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import recipia.feature.recipe_list_impl.ShortRecipe
import recipia.feature.recipe_list_impl.ui.components.RecipeItem

@Composable
fun RecipeListScreen() {
    val fakeRecipeList = listOf(
        ShortRecipe(
            id = "1",
            title = "Spaghetti",
            isFavorite = true,
            imageUrl = "https://naukajedzenia.pl/wp-content/uploads/2022/01/spaghetti-a-la-bolognese.5.jpg",
        ),
        ShortRecipe(
            id = "2",
            title = "Pizza",
            isFavorite = false,
            imageUrl = "https://www.thursdaynightpizza.com/wp-content/uploads/2019/02/pepperoni-broc-rabe-honey_featured-image.png"
        ),
        ShortRecipe(
            id = "3",
            title = "Sushi",
            isFavorite = true,
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBEYblMFA6Vb7MitrfwuAK6R0PIQ1scNNY-Q&s"
        ),
        ShortRecipe(
            id = "4",
            title = "Salad",
            isFavorite = false,
            imageUrl = "https://www.allrecipes.com/thmb/Q84xeMgnOJPZAUOdNyrb9dbFZr4=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/285625-ChefsSalad_MFS_006-2550ecee70ae46dbaec530a58314c99c.jpg"
        ),
        ShortRecipe(
            id = "5",
            title = "Steak",
            isFavorite = true,
            imageUrl = "https://food-images.files.bbci.co.uk/food/recipes/rib-eye_steak_with_61963_16x9.jpg"
        ),
        ShortRecipe(
            id = "6",
            title = "Burger",
            isFavorite = false,
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTLvBSYpdrmjC6s63P5oWgTiU4gPl36dkdVfQ&s"
        ),
        ShortRecipe(
            id = "7",
            title = "Pasta",
            isFavorite = true,
            imageUrl = "https://images.themodernproper.com/billowy-turkey/production/posts/PastaPrimavera_10.jpg?w=1200&h=1200&q=60&fm=jpg&fit=crop&dm=1719193287&s=0104e0b241aea73e5709db128503d749"
        ),
        ShortRecipe(
            id = "8",
            title = "Salad very long long long loooooong looooooon loooooooong loooong long name",
            isFavorite = false,
            imageUrl = "https://www.thespruceeats.com/thmb/2GoDt7juuaU7lxtQDq-_IjEr6t8=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/SES-our-favorite-simple-green-salad-recipe-7370448-hero-01-4ca9788a0a3c4d53b70f1d07f8382b7f.jpg"
        ),
        ShortRecipe(
            id = "9",
            title = "Very long long long long name Pizza",
            isFavorite = true,
            imageUrl = "https://recipesblob.oetker.in/assets/d8a4b00c292a43adbb9f96798e028f01/1272x764/pizza-pollo-arrostojpg.webp"
        ),
    )

    Box {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(fakeRecipeList) { recipe ->
                RecipeItem(
                    title = recipe.title,
                    imageUrl = recipe.imageUrl,
                    isFavorite = recipe.isFavorite,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}