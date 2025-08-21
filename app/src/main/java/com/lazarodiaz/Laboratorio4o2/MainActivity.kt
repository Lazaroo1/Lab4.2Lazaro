package com.lazarodiaz.Laboratorio4o2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.lazarodiaz.Laboratorio4o2.ui.theme.ThemeMyApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThemeMyApplication {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecipeScreen()
                }
            }
        }
    }
}

@Composable
fun RecipeScreen() {
    var nameInput by remember { mutableStateOf(TextFieldValue("")) }
    var urlInput by remember { mutableStateOf(TextFieldValue("")) }
    val recipeList = remember { mutableStateListOf<Recipe>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Textfield para el nombre de la receta
        TextField(
            value = nameInput,
            onValueChange = { nameInput = it },
            label = { Text("Nombre de la receta") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Textfield para el URL de la imagen
        TextField(
            value = urlInput,
            onValueChange = { urlInput = it },
            label = { Text("URL de la imagen") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Botón para agregar receta
        Button(
            onClick = {
                val name = nameInput.text.trim()
                val url = urlInput.text.trim()

                if (name.isNotEmpty() && url.isNotEmpty() &&
                    recipeList.none { it.name.equals(name, ignoreCase = true) }
                ) {
                    recipeList.add(Recipe(name, url))
                    nameInput = TextFieldValue("")
                    urlInput = TextFieldValue("")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(50) // estilo píldora
        ) {
            Text("Agregar", style = MaterialTheme.typography.labelLarge)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // La lista de recetas en Lazycolumn
        LazyColumn {
            items(recipeList) { recipe ->
                RecipeCard(
                    recipe = recipe,
                    onClick = { recipeList.remove(recipe) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

// Data class para representar la receta
data class Recipe(
    val name: String,
    val imageUrl: String
)

// Composable que muestra una card con imagen y texto
@Composable
fun RecipeCard(recipe: Recipe, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = recipe.name,
                modifier = Modifier
                    .size(72.dp)
                    .clip(MaterialTheme.shapes.small)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = recipe.name,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Start
            )
        }
    }
}
