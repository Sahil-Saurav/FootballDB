package com.example.sportsdb

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun League(navController: NavController,viewModel: SportsViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current

    var itemPosition by remember {
        mutableStateOf(0)
    }
    val listofLeague = listOf("English Premier League","Spanish La Liga","German Bundesliga","French Ligue 1","Italian Serie A")
    var League by remember {
        mutableStateOf("Click Here!!")
    }
    var dropdownState by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Select League: ")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row() {
                    TextField(
                        enabled = false,
                        value = League,
                        onValueChange = {},
                        trailingIcon = {
                            if(dropdownState){
                                Icon(Icons.Default.KeyboardArrowUp,null)
                            }else{
                                Icon(Icons.Default.KeyboardArrowDown,null)}
                        },
                        modifier = Modifier
                            .clickable(
                                onClick = {dropdownState=true}
                            )
                    )
                    DropdownMenu(
                        expanded = dropdownState,
                        onDismissRequest = {dropdownState = false}
                    ) {
                        listofLeague.forEachIndexed{index,league->
                            DropdownMenuItem(
                                text = { Text(league)},
                                onClick = {
                                    dropdownState = false
                                    itemPosition = index
                                    League = listofLeague[itemPosition]
                                }
                            )
                        }
                    }
                }
            }
        }
        Button(
            onClick = {
                keyboardController?.hide()
                if(isNetworkAvailable(context)){
                    viewModel.getLeague(League)
                    navController.navigate(Screens.LeagueList.route)
                }else{
                    Toast.makeText(context,"Internet access Required!",Toast.LENGTH_SHORT).show()
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