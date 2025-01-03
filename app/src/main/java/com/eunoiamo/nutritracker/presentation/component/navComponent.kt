package com.eunoiamo.nutritracker.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.eunoiamo.nutritracker.R
import com.eunoiamo.nutritracker.presentation.pages.HomeScreen
import com.eunoiamo.nutritracker.ui.theme.NutriTrackerTheme
import com.eunoiamo.nutritracker.ui.theme.blue100
import com.eunoiamo.nutritracker.ui.theme.blue300
import com.eunoiamo.nutritracker.ui.theme.blue500
import com.eunoiamo.nutritracker.ui.theme.blue800
import com.eunoiamo.nutritracker.ui.theme.gray200
import com.eunoiamo.nutritracker.ui.theme.gray300
import com.eunoiamo.nutritracker.ui.theme.orange400

@Preview
@Composable
private fun NavPreview() {
    val navController = rememberNavController()

    TopBarMainPage(
        navController = navController,
        isDarkMode = false,
        onThemeToggle = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithBackButton(
    navController: NavController,
) {
    TopAppBar(
        title = {
            Icon(
                painter = painterResource(id = R.drawable.chevron_back_circle),
                contentDescription = "Back",
                tint = blue500,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .clickable { navController.popBackStack() }
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarMainPage(
    navController: NavController,
    isDarkMode: Boolean,
    onThemeToggle: () -> Unit
) {

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = if (isDarkMode) Color.Black else Color.White
        ),
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 16.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = blue500,
                )
                Text(
                    text = "Nutri Trackers",
                    color = if (isDarkMode) Color.White else Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Icon(
                    painter = painterResource(if (isDarkMode) R.drawable.sun else R.drawable.moon),
                    contentDescription = "Toggle Theme",
                    tint = if (isDarkMode) Color.White else blue500,
                    modifier = Modifier
                        .clickable { onThemeToggle() }
                        .size(24.dp)
                )
            }
        }
    )
    NutriTrackerTheme(darkTheme = isDarkMode) {
    }
}


@Composable
fun BottomNavigationBar(navController: NavController, isDarkMode: Boolean) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = if (isDarkMode) Color.Black else blue500, // Change background color based on theme
        tonalElevation = 4.dp,
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp))
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text(text = "Home") },
            selected = currentDestination?.route == "home" || currentDestination?.route == "homeScreen",
            onClick = {
                navController.navigate("homeScreen") {
                    popUpTo("homeScreen") { saveState = true }
                    launchSingleTop = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor =  blue500,
                unselectedIconColor =  Color.White,
                selectedTextColor =  Color.White,
                unselectedTextColor =Color.White
            )
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "InputForm") },
            label = { Text(text = "Predict") },
            selected = currentDestination?.route == "inputform",
            onClick = {
                navController.navigate("inputform") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor =  blue500,
                unselectedIconColor =  Color.White,
                selectedTextColor =  Color.White,
                unselectedTextColor =Color.White
            )
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
            label = { Text(text = "Settings") },
            selected = currentDestination?.route == "settings",
            onClick = {
                navController.navigate("settings") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor =  blue500,
                unselectedIconColor =  Color.White,
                selectedTextColor =  Color.White,
                unselectedTextColor =Color.White
            )
        )
    }
}
