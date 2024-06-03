package com.example.jforcehiringtask.viewModels

sealed class Events {
   // data class NavigateTo(val route: String) : Events()
    data class SelectedIndex(val index: Int?) : Events()
    data class SelectedCandidate(val candidate: String) : Events()
    data class isCandidateVoted(val votingStatus: Boolean) : Events()
    data class increaseVotingCount(val index : Int) : Events()
}