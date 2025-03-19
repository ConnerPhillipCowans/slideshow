package com.example.slideshow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.slideshow.ui.theme.SlideshowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SlideshowTheme {
                SlideshowScreen()
            }
        }
    }
}


@Composable
fun SlideshowScreen() {
    val images = listOf(
        R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4
    )
    val captions = listOf("Hello! I am picture 1", "Hello! I am picture 2", "Hello! I am picture 3", "Hello! I am picture 4")

    var currentIndex by remember { mutableIntStateOf(0) }
    var textInput by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Cute Animal Slide",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))


        Image(
            painter = painterResource(id = images[currentIndex]),
            contentDescription = captions[currentIndex],
            modifier = Modifier.size(300.dp)
        )


        Text(
            text = captions[currentIndex],
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))


        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                currentIndex = (currentIndex - 1 + images.size) % images.size
            }) {
                Text("Back")
            }

            Button(onClick = {
                currentIndex = (currentIndex + 1) % images.size
            }) {
                Text("Next")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "Already know exactly where you want to go?",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(8.dp))


        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = textInput,
                onValueChange = { textInput = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier
                    .width(80.dp)
                    .background(Color.White)
                    .padding(8.dp),
                singleLine = true
            )

            Button(onClick = {
                val input = textInput.text.toIntOrNull()
                if (input != null && input in 1..images.size) {
                    currentIndex = input - 1
                }
            }) {
                Text("Go")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SlideshowPreview() {
    SlideshowTheme {
        SlideshowScreen()
    }
}