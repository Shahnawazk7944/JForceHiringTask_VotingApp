package com.example.jforcehiringtask.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyViewModel() : ViewModel() {
    private val _state =
        MutableStateFlow(States())
    val state = _state.asStateFlow()


    fun onEvent(event: Events) {
        when (event) {
            is Events.SelectedIndex -> {
                _state.update {
                    it.copy(
                        selectedIndex = event.index,
                    )
                }
            }

            is Events.SelectedCandidate -> {
                _state.update {
                    it.copy(
                        selectedCandidate = event.candidate,
                    )
                }
            }

            is Events.isCandidateVoted -> {
                _state.update {
                    it.copy(
                        isCandidateVoted = event.votingStatus,
                    )
                }
            }

            is Events.increaseVotingCount -> {
                _state.update {
                    it.copy(
                        candidatesVotesCounts = it.candidatesVotesCounts.apply {
                            this.set(event.index, state.value.candidatesVotesCounts[event.index] + 1)
                        }
                    )
                }
            }
        }
    }

}