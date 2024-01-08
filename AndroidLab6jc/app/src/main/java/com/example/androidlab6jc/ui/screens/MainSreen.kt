package com.example.androidlab6jc.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidlab6jc.R

@Composable
fun MainScreen() {
    Surface (
        modifier = Modifier.fillMaxSize()
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.1f))

            Text (
                text = "Cities App",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                modifier = Modifier
                    .height(250.dp),
                painter = painterResource(id = R.drawable.wroclaw),
                contentDescription = "Main Screen Image",
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = "Dawid Walkiewicz",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
            )

            Spacer(modifier = Modifier.fillMaxHeight(0.4f))

            val storageOptions = listOf("Shared Storage", "Private Storage")
            var selectedStorageOption by remember { mutableStateOf(storageOptions[0]) }

            Column(
                modifier = Modifier.padding(8.dp),
//                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                storageOptions.forEach { storageType ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = (storageType == selectedStorageOption),
                            onClick = { selectedStorageOption = storageType }
                        )
                        Text(
                            text = storageType,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(start = 2.dp)
                        )
                    }
                }
                Text(
                    buildAnnotatedString {
                        append("Value: ")
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.Magenta)
                        ) {
                            append(selectedStorageOption)
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}