package com.example.androidlab6jc.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidlab6jc.R

fun getDefaultCitiesList(): List<Triple<String, String, Int>> {
    return listOf(
        Triple("Wroclaw", "Capital of Lower Silesia", R.drawable.ic_average_city),
        Triple("Oia", "Small town on Santorini", R.drawable.ic_small_city),
        Triple("Hallstatt", "Small town in Austria", R.drawable.ic_small_city),
    )
}

@Composable
fun CitiesScreen(cities: List<Triple<String, String, Int>> = getDefaultCitiesList()) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = "List of cities",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
            )

            RecyclerView(cities)
        }

    }
}

@Preview
@Composable
fun CitiesScreenPreview() {
    CitiesScreen()
}

@Composable
fun ListItem(name: String, description: String, icon: Int) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        color = MaterialTheme.colorScheme.surface,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(2.dp),
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
                modifier = Modifier
                    .size(48.dp),
                painter = painterResource(id = icon),
                contentDescription = "City Icon"
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column (
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth(),
            ) {
                Text(
                    text = name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview
@Composable
fun ListItemPreview() {
    ListItem("City Name", "City Description", R.drawable.ic_average_city)
}

@Composable
fun RecyclerView(cities: List<Triple<String, String, Int>> = getDefaultCitiesList()) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        color = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            cities.forEach { city ->
                ListItem(city.first, city.second, city.third)
            }
        }
    }
}

@Preview
@Composable
fun RecyclerviewPreview() {
    RecyclerView()
}