package com.example.androidlab6jc.ui.navigationDrawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    onNavigationIconClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(text = "Cities App")
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        navigationIcon = {
            IconButton(
                onClick = onNavigationIconClick
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Toggle Drawer"
                )
            }
        }
    )
}

@Preview
@Composable
fun AppBarPreview() {
    AppBar(onNavigationIconClick = {})
}