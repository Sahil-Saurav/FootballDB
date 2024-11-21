package com.example.sportsdb

import android.graphics.Paint.Align
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController,viewModel: SportsViewModel) {
    var teamName by remember {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id=R.color.bottomback))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .height(64.dp)
            ) {
                Text(
                    text = "FootballDB",
                    color = colorResource(id=R.color.bottomcontent),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    colors = CardColors(
                        containerColor = colorResource(R.color.bottomback),
                        contentColor = colorResource(R.color.white),
                        disabledContentColor = colorResource(R.color.bottomcontent) ,
                        disabledContainerColor = colorResource(R.color.white),
                    )
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .width(320.dp)
                    ) {
                        Text(
                            text="To Know The Team Details!!",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id= R.color.bottomcontent),
                            modifier = Modifier.padding(2.dp)
                        )
                        TextField(
                            value = teamName,
                            onValueChange = {teamName=it},
                            singleLine = true,
                            label = { Text("Enter Team Name:")},
                            modifier = Modifier
                                .padding(16.dp,4.dp,16.dp,4.dp)
                        )
                        Button(
                            onClick = {
                                keyboardController?.hide()
                                if(isNetworkAvailable(context)){
                                    viewModel.getTeam(teamName)
                                    navController.navigate(Screens.TeamDetails.route)
                                }else{
                                    Toast.makeText(context,"Internet connection failure",Toast.LENGTH_SHORT).show()
                                }
                            },
                            colors = ButtonColors(
                                containerColor = colorResource(R.color.bottomcontent),
                                contentColor = colorResource(R.color.white),
                                disabledContentColor = colorResource(R.color.bottomcontent) ,
                                disabledContainerColor = colorResource(R.color.white),
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null
                            )
                        }
                    }
                }
                Spacer(
                    modifier = Modifier
                    .height(16.dp)
                )
                Card(
                    colors = CardColors(
                        containerColor = colorResource(R.color.bottomback),
                        contentColor = colorResource(R.color.white),
                        disabledContentColor = colorResource(R.color.bottomcontent) ,
                        disabledContainerColor = colorResource(R.color.white),
                    ),
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Screens.LeagueSearch.route)
                        }
                ){
                    Box(
                        contentAlignment = Alignment.Center,
                    ) {
                        Column {
                           Row(
                               verticalAlignment = Alignment.CenterVertically,
                               horizontalArrangement = Arrangement.Absolute.Center,
                               modifier = Modifier
                                   .width(320.dp)
                           ) {
                               Icon(
                                   painterResource(R.drawable.baseline_sports_24),
                                   null,
                                   tint = colorResource(R.color.bottomcontent),
                                   modifier = Modifier
                                       .size(48.dp)
                               )
                               Spacer(
                                   modifier = Modifier
                                       .width(16.dp))
                               Text(
                                   text = "Search By League",
                                   fontSize = 18.sp,
                                   fontWeight = FontWeight.Bold,
                                   color = colorResource(id = R.color.bottomcontent),
                                   modifier = Modifier
                                       .padding(2.dp)
                               )
                           }
                        }
                    }
                }
                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                )
                Card(
                    colors = CardColors(
                        containerColor = colorResource(R.color.bottomback),
                        contentColor = colorResource(R.color.white),
                        disabledContentColor = colorResource(R.color.bottomcontent) ,
                        disabledContainerColor = colorResource(R.color.white),
                    ),
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Screens.Standings.route)
                        }
                ){
                    Box(
                        contentAlignment = Alignment.Center,
                    ) {
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Absolute.Center,
                                modifier = Modifier
                                    .width(320.dp)
                            ) {
                                Icon(
                                    painterResource(R.drawable.baseline_sports_soccer_24),
                                    null,
                                    tint = colorResource(R.color.bottomcontent),
                                    modifier = Modifier
                                        .size(48.dp)
                                )
                                Spacer(
                                    modifier = Modifier
                                        .width(16.dp))
                                Text(
                                    text = "League Standings",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = colorResource(id = R.color.bottomcontent),
                                    modifier = Modifier
                                        .padding(2.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
