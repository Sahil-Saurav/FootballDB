package com.example.sportsdb

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.sportsdb.api.NetworkResponse
import com.example.sportsdb.api.TeamDetails

@Composable
fun TeamDetails(navController: NavController,viewModel: SportsViewModel){
    val teamDetails = viewModel.teamDetails.observeAsState()
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .background(color = colorResource(R.color.bottomback))
                .fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .height(64.dp)
            ) {
                Icon(Icons.Default.ArrowBack,
                    null,
                    tint = colorResource(R.color.bottomcontent),
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { navController.navigateUp()}
                    )
                Text(
                    " Team Details",
                    modifier = Modifier
                        .padding(4.dp),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = colorResource(R.color.bottomcontent)
                )
            }
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .background(Color.White)
                    .fillMaxSize()
            ) {
                when(val result = teamDetails.value){
                    is NetworkResponse.Error ->{
                        Text(result.message?:"An error occurred")
                    }
                    is NetworkResponse.Loading ->{
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            CircularProgressIndicator()
                        }
                    }
                    is NetworkResponse.Success ->{
                        result.data.let { data ->
                            if (data.teams.isNullOrEmpty()) {
                                Text("No team found!")
                            } else {
                                Details(data,navController)
                            }
                        }
                    }
                    null ->{
                        Text("Loading Data..")
                    }
                }
            }
        }
    }
}

@Composable
fun Details(data:TeamDetails,navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        data.teams.let {
            teams -> teams.forEach {
                team ->
                Text(
                    text = "Name: ${team.strTeam?:"unknown team!!"}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                    )
                AsyncImage(
                    model = team.strBadge,
                    contentDescription = "Team Badge",
                    modifier = Modifier
                        .size(160.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(Color.LightGray)
                )
            Text(
                text = "Estd: ${team.intFormedYear}",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally)
            )
            if(team.strKeywords.isNullOrEmpty()){

            }else{
                Text(
                    text = "NickName: ${team.strKeywords}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(4.dp)
                )
            }
            Text(
                text = "League: ${team.strLeague}",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(4.dp)
            )
            Text(
                text = "Stadium Details:->",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier
                    .padding(4.dp)
                    .background(Color.Gray)
                    .fillMaxWidth()
            )
            Text(
                text = "Name: ${team.strStadium}",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(4.dp)
            )
            Text(
                text = "Capacity: ${team.intStadiumCapacity}",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(4.dp)
            )
            Text(
                text = "Location: ${team.strLocation}",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(4.dp)
            )
            Text(
                text = "About:->",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier
                    .padding(4.dp)
                    .background(Color.Gray)
                    .fillMaxWidth()
            )
            if(team.strBanner.isNullOrEmpty()){

            }else{
                AsyncImage(
                    model = team.strBanner,
                    contentDescription = "Banner",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(100.dp)
                )
            }
            Text(
                text = team.strDescriptionEN,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(4.dp)
            )
            Button(
                onClick = {navController.navigate(Screens.Home.route)},
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .background(
                        color=colorResource(R.color.bottomcontent),
                        shape = RoundedCornerShape(20.dp)
                        )
            ) {
                Text("Back To Home")
                }
            Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
