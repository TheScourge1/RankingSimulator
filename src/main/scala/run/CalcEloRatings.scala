package run

import rankingSim.dao._
import rankingSim.models.PlayerRank
import rankingSim.processor.EloRating

import scala.concurrent.duration._
import scala.concurrent.Await
import scala.util.{Failure, Success}

import scala.concurrent._
import ExecutionContext.Implicits.global


object CalcEloRatings extends App{

  val inputMatchesDAO = InputMatchDAO
  val playerRankDAO = PlayerRankDAO
  val rankingPointsDAO = RankingPointsDAO
  val playerRank = PlayerRankDAO

  val inputMatches = inputMatchesDAO.getAllHE
  val playerRanks = playerRankDAO.getAll

  var retrievedMatches = Await.ready(inputMatches,1 minute).value.get.get
  var players = Await.ready(playerRanks,1 minute).value.get.get
  println(s"Matches found: "+retrievedMatches.size)
  println(s"Players found: "+players.size)

  var kFunction = (r1 :PlayerRank,r2 : PlayerRank) => 32

 /* val rankResults = for {
    matches <- inputMatches
    players <- playerRanks
    if(matches.size > 0 && players.size  > 0)
  } yield inputMatches.map(matchList => matchList.map(game => EloRating.processSinglesGame(playerRanks.value.get.get,game,kFunction)))
*/
 // inputMatches = inputMatches.filter(game => game.head.team1Player1MemberId == "50047635" || game.head.team1Player2MemberId == "50047635"  )

  retrievedMatches = retrievedMatches.filter(game => game.team1Player1MemberId == "50047635" || game.team2Player1MemberId == "50047635" )

  retrievedMatches = retrievedMatches.sortWith((game1,game2) => game1.matchDate.before(game2.matchDate))
  val missingPlayers = EloRating.createMissingPlayers(players,retrievedMatches)
  players++=missingPlayers
  val rankResult = retrievedMatches.map(game => EloRating.processSinglesGame(players,game,kFunction))


  rankResult.foreach(r => println(r))


/*
  rankResults.onComplete {
    case Success(value) => println("The final value: "+value)
    case Failure(e) => println("Error: ");e.printStackTrace()
  }
*/
}
