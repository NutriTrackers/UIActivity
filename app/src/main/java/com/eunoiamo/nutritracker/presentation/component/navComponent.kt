package com.eunoiamo.nutritracker.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.eunoiamo.nutritracker.R
import com.eunoiamo.nutritracker.ui.theme.blue300
import com.eunoiamo.nutritracker.ui.theme.blue500
import com.eunoiamo.nutritracker.ui.theme.gray200
import com.eunoiamo.nutritracker.ui.theme.gray300
import com.eunoiamo.nutritracker.ui.theme.orange400


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
        }
    )
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = blue500,
        tonalElevation = 4.dp,
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp))
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text(text = "Home") },
            selected = currentDestination?.route == "home",
            onClick = {
                navController.navigate("home") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = blue500,
                unselectedIconColor = Color.White,
                selectedTextColor = blue500,
                unselectedTextColor = Color.White
            )
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text(text = "Profile") },
            selected = currentDestination?.route == "profile",
            onClick = {
                navController.navigate("profile") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = blue500,
                unselectedIconColor = Color.White,
                selectedTextColor = blue500,
                unselectedTextColor = Color.White
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
                selectedIconColor = blue500,
                unselectedIconColor = Color.White,
                selectedTextColor = blue500,
                unselectedTextColor = Color.White
            )
        )
    }
}
