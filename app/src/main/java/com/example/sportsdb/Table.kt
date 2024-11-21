package com.example.sportsdb

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.example.sportsdb.api.NetworkResponse
import com.example.sportsdb.api.Table

@Composable
fun Table(navController: NavController,viewModel: SportsViewModel){
    val tableList = viewModel.tableList.observeAsState()
    val league by remember {
        mutableStateOf("")
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
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
                Icon(
                    Icons.Default.ArrowBack,
                    null,
                    tint = colorResource(R.color.bottomcontent),
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Screens.Standings.route)
                        }
                )
                Text(
                    text = "Standings",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(R.color.bottomcontent),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                when(val result = tableList.value){
                    is NetworkResponse.Loading->{
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    is NetworkResponse.Success -> {
                        Box(
                            contentAlignment = Alignment.Center,
                        ) {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                item {
                                    Box {
                                        Row(

                                        ){
                                            TableCell(
                                                text = "Pos",
                                                weight = 1f,
                                                title = true
                                            )
                                            TableCell(
                                                text = "Team",
                                                weight = 3f,
                                                title = true
                                            )
                                            TableCell(
                                                text = "P",
                                                weight = 0.5f,
                                                title = true
                                            )
                                            TableCell(
                                                text = "W",
                                                weight = 0.5f,
                                                title = true
                                            )
                                            TableCell(
                                                text = "D",
                                                weight = 0.5f,
                                                title = true
                                            )
                                            TableCell(
                                                text = "L",
                                                weight = 0.5f,
                                                title = true
                                            )
                                            TableCell(
                                                text = "GF",
                                                weight = 0.6f,
                                                title = true
                                            )
                                            TableCell(
                                                text = "GA",
                                                weight = 0.6f,
                                                title = true
                                            )
                                            TableCell(
                                                text = "GD",
                                                weight = 0.7f,
                                                title = true
                                            )
                                            TableCell(
                                                text = "Pts",
                                                weight = 1f,
                                                title = true
                                            )
                                        }
                                    }
                                }
                                items(result.data.table){
                                        data->
                                    TableRow(data)
                                }
                            }
                        }
                    }
                    is NetworkResponse.Error -> {
                        Text(result.message?:"Error Occurred!!")
                    }
                    null -> {
                        Text("Error Occurred!!")
                    }
                }
            }
        }
    }
}

@Composable
fun TableRow(data:Table){
    Box(
        modifier = Modifier.padding(2.dp)
    ) {
        Row(
        ) {

            TableCell(
                text = data.intRank,
                weight = 1f
            )
//            AsyncImage(
//                model = data.strBadge,
//                contentDescription = null,
//                modifier = Modifier.size(18.dp)
//            )
            TableCell(
                text = data.strTeam,
                weight = 2.5f,
                image = true,
                imageLink = data.strBadge
                )
            TableCell(
                text = data.intPlayed,
                weight = 0.5f
            )
            TableCell(
                text = data.intWin,
                weight = 0.5f
            )
            TableCell(
                text = data.intDraw,
                weight = 0.5f
            )
            TableCell(
                text = data.intLoss,
                weight = 0.5f
            )
            TableCell(
                text = data.intGoalsFor,
                weight = 0.6f
            )
            TableCell(
                text = data.intGoalsAgainst,
                weight = 0.6f
            )
            TableCell(
                text = data.intGoalDifference,
                weight = 0.7f
            )
            TableCell(
                text = data.intPoints,
                weight = 1f
            )
        }
    }
    HorizontalDivider()
}

@Composable
fun RowScope.TableCell(
    text:String,
    weight:Float,
    alignment: TextAlign = TextAlign.Center,
    title:Boolean = false,
    image:Boolean = false,
    imageLink: String = String.toString(),
    )
{
    if(image){
        AsyncImage(imageLink,null, modifier = Modifier.size(24.dp))
    }
    Text(
            text=text,
            textAlign = alignment,
            fontWeight = if(title) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier
                .weight(weight)
                .padding(1.dp)
        )
}