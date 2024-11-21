package com.example.sportsdb

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsdb.api.LeagueDetails
import com.example.sportsdb.api.NetworkResponse
import com.example.sportsdb.api.Standings
import com.example.sportsdb.api.Table
import com.example.sportsdb.api.TeamDetails
import com.example.sportsdb.api.retrofitInstance
import kotlinx.coroutines.launch

class SportsViewModel :ViewModel(){
    private val sportsApi = retrofitInstance.sportsApi

    private val _teamDetails = MutableLiveData<NetworkResponse<TeamDetails>>()
    val teamDetails:LiveData<NetworkResponse<TeamDetails>> = _teamDetails

    private val _leagueList = MutableLiveData<NetworkResponse<LeagueDetails>>()
    val leagueList :LiveData<NetworkResponse<LeagueDetails>> = _leagueList

    private val _tableList = MutableLiveData<NetworkResponse<Standings>>()
    val tableList: LiveData<NetworkResponse<Standings>> = _tableList

    fun getTeam(name:String){
        _teamDetails.value = NetworkResponse.Loading
        viewModelScope.launch {
            val response = sportsApi.getTeam(name)
            try {
                if (response.isSuccessful){
                    response.body()?.let {
                        _teamDetails.value = NetworkResponse.Success(it)
                    }
                }else{
                    _teamDetails.value = NetworkResponse.Error("Failed to Load data")
                }
            }catch (e:Exception){
                _teamDetails.value = NetworkResponse.Error("There is an Exception in the API")
            }
        }
    }
    fun getLeague(name:String){
        _leagueList.value = NetworkResponse.Loading
        viewModelScope.launch {
            val response = sportsApi.getLeague(name)
            try {

                if(response.isSuccessful){
                    response.body()?.let {
                        _leagueList.value = NetworkResponse.Success(it)
                    }
                }
                else{
                    _leagueList.value = NetworkResponse.Error("Failed to Load data!!")
                }
            }catch (e:Exception){
                _teamDetails.value = NetworkResponse.Error("There is Exception!! in Api")
            }
        }
    }
    fun getStandings(id:String,season:String){
        _tableList.value = NetworkResponse.Loading
        viewModelScope.launch {
            val response = sportsApi.getStandings(id,season)
            try {
                if(response.isSuccessful){
                    response.body()?.let {
                        _tableList.value = NetworkResponse.Success(it)
                    }
                }else{
                    _tableList.value = NetworkResponse.Error("Failed To load data!!")
                }
            }catch (e:Exception){
                _teamDetails.value = NetworkResponse.Error("There is an exception in API")
            }
        }
    }
}