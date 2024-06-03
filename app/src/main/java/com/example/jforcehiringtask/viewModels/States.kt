package com.example.jforcehiringtask.viewModels

data class States(
    val isCandidateVoted : Boolean = false,
    val selectedIndex : Int? = null,
    val selectedCandidate : String? = null,
    val candidates : List<String> = listOf(
        "Sam Yas",
        "Zeru Ka",
        "Saif Sae",
        "Faraz IK"
    ),

    val candidatesVotesCounts : MutableList<Int> = mutableListOf(
       0,0,0,0
    ),

    val adminEmail : String = "Admin@gmail.com",
    val adminPassword : String = "123456",

)