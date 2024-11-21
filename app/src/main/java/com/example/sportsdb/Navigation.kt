package com.example.sportsdb

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(navController: NavHostController = rememberNavController(),viewModel: SportsViewModel){
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = colorResource(R.color.bottomback),
                contentColor = colorResource(id = R.color.bottomcontent),
                modifier = Modifier
                    .background(Color.Transparent)
                    .clip(RoundedCornerShape(64.dp,64.dp,0.dp,0.dp))
                    .height(64.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(color = Color.Transparent)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Transparent)
                    ) {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = null,
                            modifier = Modifier
                                .clickable {navController.navigate(Screens.Home.route)}
                                .size(32.dp)
                        )
                        VerticalDivider(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(8.dp),
                            thickness = 2.dp,
                            color = colorResource(id = R.color.bottomcontent)
                        )
                        Icon(
                            Icons.Default.Search,
                            contentDescription = null,
                            modifier = Modifier
                                .clickable {navController.navigate(Screens.LeagueSearch.route)}
                                .size(32.dp)
                        )
                        VerticalDivider(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(8.dp),
                            thickness = 2.dp,
                            color = colorResource(id = R.color.bottomcontent)
                        )
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = null,
                            modifier = Modifier
                                .clickable {navController.navigate(Screens.Standings.route)}
                                .size(32.dp)
                        )
                    }
                }
            }
        }
    ) {
        innerPadding->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = Screens.Home.route
            ) {
                composable(route = Screens.Home.route){
                    HomeScreen(navController,viewModel)
                }
                composable(route = Screens.Standings.route){
                    Standings(navController,viewModel)
                }
                composable(route = Screens.LeagueSearch.route){
                    League(navController,viewModel)
                }
                composable(route=Screens.TeamDetails.route){
                    TeamDetails(navController,viewModel)
                }
                composable(route=Screens.LeagueList.route){
                    LeagueList(navController,viewModel)
                }
                composable(route= Screens.Table.route){
                    Table(navController,viewModel)
                }
            }
        }
    }
}

@Composable
fun HomeCard(content:@Composable () -> Unit = {}){
    Card {

    }
}