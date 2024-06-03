package com.example.jforcehiringtask

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.example.jforcehiringtask.components.PrimaryButton
import com.example.jforcehiringtask.ui.theme.FPrimaryBackground
import com.example.jforcehiringtask.ui.theme.FPrimaryBlack
import com.example.jforcehiringtask.ui.theme.FPrimaryGreen
import com.example.jforcehiringtask.ui.theme.FSecondaryBackgroundWhite
import com.example.jforcehiringtask.ui.theme.PaleYellow
import com.example.jforcehiringtask.ui.theme.poppins
import com.example.jforcehiringtask.viewModels.Events
import com.example.jforcehiringtask.viewModels.MyViewModel
import com.example.jforcehiringtask.viewModels.States
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavHostController,
    myViewModel: MyViewModel,
    state: States
) {
    val scope = rememberCoroutineScope()
    val snackbarState = remember {
        SnackbarHostState()
    }
    Scaffold(
        containerColor = FPrimaryBackground,
        topBar = {
            MyTopAppBar(
                onClick = { },
                title = {
                    SnackbarHost(
                        hostState = snackbarState,
                    ) {
                        Snackbar(
                            action = {
                                Text(
                                    text = it.visuals.actionLabel!!,
                                    fontFamily = poppins,
                                    fontSize = 12.sp,
                                    color = PaleYellow,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.clickable { snackbarState.currentSnackbarData?.dismiss() }
                                )
                            },
                            containerColor = Color(0xE9791E33),
                            contentColor = FSecondaryBackgroundWhite,
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = it.visuals.message,
                                fontFamily = poppins,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                            )
                        }
                    }
                },
                action = {
                    IconButton(onClick = {
                        myViewModel.onEvent(Events.isCandidateVoted(false))
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
                "Voting Solution!",
                fontFamily = poppins,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = FPrimaryBlack,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 0.dp, start = 5.dp)
            )
            Text(
                "Be honest with your vote",
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
                        VoteCard(
                            candidateName = state.candidates[index],
                            index = index,
                            state = state,
                            onClick = { index, candidateName ->
                                myViewModel.onEvent(Events.SelectedIndex(index))
                                myViewModel.onEvent(Events.SelectedCandidate(candidateName))
                            }
                        )
                    }
                }

            }




            Spacer(modifier = Modifier.height(30.dp))
            if (state.isCandidateVoted) {
                Text(
                    "You Already Voted to ${state.selectedCandidate}",
                    fontFamily = poppins,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xE9791E33),
                    textAlign = TextAlign.Center,
                )
            } else {
                PrimaryButton(
                    onClick = {
                        if (state.selectedIndex == null) {
                            scope.launch {
                                snackbarState.currentSnackbarData?.dismiss()
                                snackbarState.showSnackbar(
                                    message = "Please Select at least one Candidate.",
                                    actionLabel = "Retry",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        } else {
                            myViewModel.onEvent(Events.isCandidateVoted(true))
                            myViewModel.onEvent(Events.increaseVotingCount(state.selectedIndex))
                            myViewModel.onEvent(Events.SelectedIndex(null))
                            scope.launch {
                                snackbarState.currentSnackbarData?.dismiss()
                                snackbarState.showSnackbar(
                                    message = "You Voted to ${state.selectedCandidate}.",
                                    actionLabel = "Retry",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }
                    },
                    eventText = "Vote",
                    leadingIconComposable = {},
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun VoteCard(
    candidateName: String,
    index: Int,
    state: States,
    onClick: (index: Int, candidateName: String) -> Unit

) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(horizontal = 10.dp)
            .padding(top = 15.dp)
            .clickable {
                onClick(index, candidateName)
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(25.dp),
        border = BorderStroke(
            2.dp,
            if (state.selectedIndex == index) Color(0xE9791E33) else FPrimaryGreen
        )
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
//            Text(
//                "${state.candidatesVotesCounts[index]}",
//                fontFamily = poppins,
//                fontSize = 22.sp,
//                fontWeight = FontWeight.Normal,
//                color = Color.Black,
//                textAlign = TextAlign.Center,
//            )
        }
    }
}

