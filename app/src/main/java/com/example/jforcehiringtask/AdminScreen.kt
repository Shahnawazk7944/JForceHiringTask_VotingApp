package com.example.jforcehiringtask

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jforcehiringtask.components.MyTopAppBar
import com.example.jforcehiringtask.ui.theme.FPrimaryBackground
import com.example.jforcehiringtask.ui.theme.FPrimaryBlack
import com.example.jforcehiringtask.ui.theme.FPrimaryGreen
import com.example.jforcehiringtask.ui.theme.poppins
import com.example.jforcehiringtask.viewModels.States

@Composable
fun AdminScreen(
    navController: NavHostController,
    state: States
) {
    Scaffold(
        containerColor = FPrimaryBackground,
        topBar = {
            MyTopAppBar(
                onClick = { },
                title = {
                },
                action = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.logout),
                            contentDescription = "back Icon",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(25.dp),
                        )
                    }
                },
                appBarLeadingIcon = painterResource(R.drawable.back)
            )
        },

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(10.dp)
                .padding(horizontal = 15.dp),
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                "Admin Panel!",
                fontFamily = poppins,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = FPrimaryBlack,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 0.dp, start = 5.dp)
            )
            Text(
                "See the voting counts",
                fontFamily = poppins,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(0.dp))

            Box(modifier = Modifier) {
                LazyColumn(
                    modifier = Modifier
                        .padding(0.dp)
                        .padding(top = 10.dp),
                    contentPadding = PaddingValues(5.dp),
                ) {

                    items(state.candidates.size) { index ->
                        AdminVoteCard(
                            candidateName = state.candidates[index],
                            index = index,
                            voteCounts = state.candidatesVotesCounts[index]
                        )
                    }
                }

            }
        }
    }
}


@Composable
fun AdminVoteCard(
    candidateName: String,
    index: Int,
    voteCounts: Int,
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(horizontal = 10.dp)
            .padding(top = 15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(25.dp),
        border = BorderStroke(
            2.dp,
            FPrimaryGreen)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                "$index",
                fontFamily = poppins,
                fontSize = 22.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                candidateName,
                fontFamily = poppins,
                fontSize = 22.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                "$voteCounts",
                fontFamily = poppins,
                fontSize = 22.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                textAlign = TextAlign.Center,
            )
        }
    }
}