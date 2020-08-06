package com.renovavision.footballapp.domain.entities

import java.io.Serializable

data class Event(
    val idEvent: Int,
    val idSoccerXML: Int?,
    val idAPIfootball: Int?,
    val strEvent: String,
    val strEventAlternate: String?,
    val strFilename: String?,
    val strSport: String,
    val idLeague: Int,
    val strLeague: String,
    val strSeason: String,
    val strDescriptionEN: String?,
    val strHomeTeam: String,
    val strAwayTeam: String,
    val intHomeScore: Int?,
    val intRound: Int?,
    val intAwayScore: Int?,
    val intSpectators: Int?,
    val strHomeGoalDetails: String?,
    val strHomeRedCards: String?,
    val strHomeYellowCards: String?,
    val strHomeLineupGoalkeeper: String?,
    val strHomeLineupDefense: String?,
    val strHomeLineupMidfield: String?,
    val strHomeLineupForward: String?,
    val strHomeLineupSubstitutes: String?,
    val strHomeFormation: String?,
    val strAwayRedCards: String?,
    val strAwayYellowCards: String?,
    val strAwayGoalDetails: String?,
    val strAwayLineupGoalkeeper: String?,
    val strAwayLineupDefense: String?,
    val strAwayLineupMidfield: String?,
    val strAwayLineupForward: String?,
    val strAwayLineupSubstitutes: String?,
    val strAwayFormation: String?,
    val intHomeShots: Int?,
    val intAwayShots: Int?,
    val dateEvent: String?,
    val dateEventLocal: String?,
    val strDate: String?,
    val strTime: String?,
    val strTimeLocal: String?,
    val strTVStation: String?,
    val idHomeTeam: Int,
    val idAwayTeam: Int,
    val strResult: String?,
    val strCircuit: String?,
    val strCountry: String,
    val strCity: String?,
    val strPoster: String?,
    val strFanart: String?,
    val strThumb: String?,
    val strBanner: String?,
    val strMap: String?,
    val strTweet1: String?,
    val strTweet2: String?,
    val strTweet3: String?,
    val strVideo: String?,
    val strPostponed: String?,
    val strLocked: String?
) : Serializable {

    fun getScore() = "$intHomeScore : $intAwayScore"

    fun getHomeSquad() =
        "${strHomeTeam}: $strHomeLineupGoalkeeper $strHomeLineupDefense $strHomeLineupMidfield $strHomeLineupForward"

    fun getAwaySquad() =
        "${strAwayTeam}: $strAwayLineupGoalkeeper $strAwayLineupDefense $strAwayLineupMidfield $strAwayLineupForward"
}