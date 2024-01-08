package com.example.androidlab6jc.ui.navigationDrawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidlab6jc.R

@Composable
fun DrawerHeader() {
    Surface (
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 50.dp,
                    bottom = 10.dp,
                    start = 20.dp
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Cities App",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview
@Composable
fun DrawerHeaderPreview() {
    DrawerHeader()
}

@Composable
fun DrawerBody(
    items: List<DrawerItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    onItemClick: (DrawerItem) -> Unit = {}
) {
    LazyColumn (modifier) {
        items(items) { item ->
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(16.dp),
            ) {
                Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = item.contentDescription,
                )
                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    modifier = Modifier.weight(1f),
                    text = item.title,
                    style = itemTextStyle
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DrawerBodyPreview() {
    DrawerBody(
        items = listOf(
            DrawerItem(
                id = "home",
                title = "Home",
                contentDescription = "Home",
                icon = R.drawable.main_icon
            ),
            DrawerItem(
                id = "cities",
                title = "Cities",
                contentDescription = "Cities",
                icon = R.drawable.ic_list
            ),
            DrawerItem(
                id = "images",
                title = "Images",
                contentDescription = "Choose Image for Main Screen",
                icon = R.drawable.ic_photo_list
            )
        )
    )
}