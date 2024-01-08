package com.example.androidlab6jc

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.androidlab6jc.ui.navigationDrawer.AppBar
import com.example.androidlab6jc.ui.navigationDrawer.DrawerBody
import com.example.androidlab6jc.ui.navigationDrawer.DrawerHeader
import com.example.androidlab6jc.ui.navigationDrawer.DrawerItem
import com.example.androidlab6jc.ui.screens.Routes
import com.example.androidlab6jc.ui.theme.AndroidLab6jcTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            AndroidLab6jcTheme {
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val coroutineScope = rememberCoroutineScope()

                ModalNavigationDrawer(
                    drawerContent = {
                        ModalDrawerSheet {
                            DrawerHeader()
                            DrawerBody(
                                items = listOf(
                                    DrawerItem(
                                        id = Routes.MAIN_SCREEN,
                                        title = "Home",
                                        contentDescription = "Home",
                                        icon = R.drawable.main_icon
                                    ),
                                    DrawerItem(
                                        id = Routes.CITIES_SCREEN,
                                        title = "Cities",
                                        contentDescription = "Cities",
                                        icon = R.drawable.ic_list
                                    ),
                                    DrawerItem(
                                        id = Routes.IMAGE_PICKER_SCREEN,
                                        title = "Images",
                                        contentDescription = "Choose Image for Main Screen",
                                        icon = R.drawable.ic_photo_list
                                    )
                                ),
                                onItemClick = {
                                    coroutineScope.launch {
                                        drawerState.close()
                                        navController.navigate(it.id)
                                    }
                                }
                            )
                        }
                    },
                    drawerState = drawerState,
                ) {
                    Scaffold(
                        topBar = {
                            AppBar (
                                onNavigationIconClick = {
                                    coroutineScope.launch {
                                        drawerState.open()
                                    }
                                }
                            )
                        }
                    ) {
                        AndroidLab6jcApp(navController, it)
                    }

                }
            }
        }
    }
}

@Composable
fun AndroidLab6jcApp(navController: NavHostController, barsPadding: PaddingValues) {
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .padding(barsPadding)
    ) {
        NavigationGraph(navController)
    }

}