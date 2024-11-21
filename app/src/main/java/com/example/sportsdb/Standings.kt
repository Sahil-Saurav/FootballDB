package com.example.sportsdb

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.navigation.NavController

@Composable
fun Standings(navController: NavController,viewModel: SportsViewModel){
    val Utility = Utils()
    val keyboardController = LocalSoftwareKeyboardController.current
    var League by remember {
        mutableStateOf("")
    }
    var Season by remember {
        mutableStateOf("")
    }
    var leagueDropDownState by remember {
        mutableStateOf(false)
    }
    var seasonDropDownState by remember {
        mutableStateOf(false)
    }
    val lisOfLeague = listOf("Premier League","La Liga","Bundesliga","Serie A","Ligue 1")
    var indexLeague by remember {
        mutableStateOf(0)
    }
    val listOfSeason = listOf("2024-2025","2023-2024","2022-2023","2021-2022","2020-2021")
    var indexSeason by remember {
        mutableStateOf(0)
    }
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)) {
        Box(
            modifier = Modifier
                .background(Color.White)
        ){
            TextField(
                value = League,
                onValueChange = {League=it},
                label = { Text("League")},
                enabled = false,
                trailingIcon = {
                    if(leagueDropDownState){
                        Icon(Icons.Default.KeyboardArrowUp,null)
                    }else{
                        Icon(Icons.Default.KeyboardArrowDown,null)}
                },
                modifier = Modifier
                    .clickable(
                        onClick = {
                            leagueDropDownState = true
                        }
                    )
            )
            DropdownMenu(
                expanded = leagueDropDownState,
                onDismissRequest = {leagueDropDownState=false}
            ) {
                lisOfLeague.forEachIndexed { index, league ->
                    DropdownMenuItem(
                        text = { Text(league)},
                        onClick = {
                            leagueDropDownState = false
                            indexLeague = index
                            League = lisOfLeague[index]
                            keyboardController?.hide()
                            Log.i("lolo",League)
                        }
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .background(Color.White)
        ){
            TextField(
                value = Season,
                onValueChange = {},
                label = { Text("Season")},
                enabled = false,
                trailingIcon = {
                    if(seasonDropDownState){
                        Icon(Icons.Default.KeyboardArrowUp,null)
                    }else{
                        Icon(Icons.Default.KeyboardArrowDown,null)}
                },
                modifier = Modifier
                    .clickable(
                        onClick = {
                            seasonDropDownState = true
                        }
                    )
            )
            DropdownMenu(
                expanded = seasonDropDownState,
                onDismissRequest = {seasonDropDownState = false}
            ) {
                listOfSeason.forEachIndexed { index, season ->
                    DropdownMenuItem(
                        text = {Text(season)},
                        onClick = {
                            seasonDropDownState = false
                            indexSeason = index
                            Season = listOfSeason[index]
                            keyboardController?.hide()
                            Log.i("loloseason",Season)
                        }
                    )
                }
            }
        }
        Button(
            onClick = {
                if (isNetworkAvailable(context)){
                    viewModel.getStandings(Utility.leagueToId(League),Season)
                    navController.navigate(Screens.Table.route)
                }else{
                    Toast.makeText(context,"Internet access required!!",Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonColors(
                containerColor = colorResource(R.color.bottomcontent),
                contentColor = colorResource(R.color.white),
                disabledContentColor = colorResource(R.color.bottomcontent) ,
                disabledContainerColor = colorResource(R.color.white),
            )

        ) {
            Icon(Icons.Default.Search,null)
        }

    }
}
