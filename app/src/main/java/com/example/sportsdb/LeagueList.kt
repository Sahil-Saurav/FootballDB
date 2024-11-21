package com.example.sportsdb

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.sportsdb.api.NetworkResponse
import com.example.sportsdb.api.TeamX

@Composable
fun LeagueList(navController: NavController,viewModel: SportsViewModel){
    val leagueList = viewModel.leagueList.observeAsState()
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
          Column(
              modifier = Modifier
                  .background(colorResource(id = R.color.bottomback))
                  .fillMaxSize()
          ) {
              Row(
                  horizontalArrangement = Arrangement.Start,
                  verticalAlignment = Alignment.CenterVertically,
                  modifier = Modifier
                      .height(64.dp)
              ) {
                  Icon(Icons.Default.ArrowBack,
                      null,
                      tint = colorResource(id = R.color.bottomcontent),
                      modifier = Modifier
                          .size(32.dp)
                          .clickable { navController.navigate(Screens.LeagueSearch.route) }
                  )
                  Text(
                      text = "Team List",
                      modifier = Modifier
                          .padding(6.dp)
                          .fillMaxWidth(),
                      fontSize = 28.sp,
                      fontWeight = FontWeight.ExtraBold,
                      color = colorResource(R.color.bottomcontent)
                  )
              }
              Column (
                    modifier = Modifier
                        .background(Color.White)
              ){
                  when(val result = leagueList.value){
                      is NetworkResponse.Error->{
                          Text(result.message?:"An error Occurred")
                      }

                      is NetworkResponse.Loading ->{
                          Box(
                              modifier = Modifier
                                  .fillMaxSize(),
                              contentAlignment = Alignment.Center
                          ) {
                              CircularProgressIndicator()
                          }
                      }
                      is NetworkResponse.Success ->{
                          LazyVerticalGrid(
                              columns = GridCells.Fixed(2),
                              modifier = Modifier
                                  .fillMaxSize()
                          ) {
                              items(result.data.teams){
                                  data-> TeamCard(data,navController,viewModel,context)
                              }
                          }
                      }
                      null -> {
                          Text("An Error Occurred!!")
                      }
                  }

              }

          }
    }
}

@Composable
fun TeamCard(data:TeamX,navController: NavController,viewModel: SportsViewModel,context:Context){
    Box(
        modifier = Modifier
            .height(240.dp)
            .border(4.dp, color = colorResource(id = R.color.bottomcontent))
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .clickable {
                    if (isNetworkAvailable(context)){
                        viewModel.getTeam(data.strTeam)
                        navController.navigate(Screens.TeamDetails.route)
                    }else{
                        Toast.makeText(context,"Internet access required!!",Toast.LENGTH_SHORT).show()
                    }
                }
        ) {
            AsyncImage(
                model = data.strBadge,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(160.dp)
            )
            Text(
                text=data.strTeam,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(2.dp)
            )
            Text(
                text= "Estd:${ data.intFormedYear }",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(2.dp)
            )
        }
    }
}