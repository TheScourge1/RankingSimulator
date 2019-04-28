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

  val inputMatchesDAO = new InputMatchRepo
  val playerRankDAO = new PlayerRankRepo
  val rankingPointsDAO = new RankingPointsRepo

  val inputMatches = inputMatchesDAO.getAllHE
  val playerRanks = playerRankDAO.getAll

  var retrievedMatches = Await.result(inputMatches,1 minute)
  var players = Await.result(playerRanks,1 minute)
  println(s"Matches found: "+retrievedMatches.size)
  println(s"Players found: "+players.size)

  var kFunction = (r1 :PlayerRank,r2 : PlayerRank) => 32

  //retrievedMatches = retrievedMatches.filter(game => game.team1Player1MemberId == "50047635" || game.team2Player1MemberId == "50047635"  )

  retrievedMatches = retrievedMatches
    .filter(game => game.winner == "1" || game.winner == "2")
    .sortWith((game1,game2) => game1.matchDate.before(game2.matchDate))

  players ++= EloRating.createMissingPlayers(players,retrievedMatches)
  var playerMap = Map.empty[String,PlayerRank]
  players.foreach(p => playerMap+=(p.playerId -> p))
  val rankResult = retrievedMatches.map(game =>
    {
      val res = EloRating.processSinglesGame(playerMap,game,kFunction)
      playerMap+=(res._1.playerId -> new PlayerRank(0,res._1.playerId,res._1.newBalance,res._1.calcDate))
      playerMap+=(res._2.playerId -> new PlayerRank(0,res._2.playerId,res._2.newBalance,res._2.calcDate))
      res
    })

  //rankResult.foreach(println)
  println("Results processed: "+rankResult.size*2)

  players.filter(p => playerMap(p.playerId).points != p.points).foreach(p => playerRankDAO.update(playerMap(p.playerId)))

  val update2 = rankingPointsDAO.save(rankResult.map(tuple => tuple._1))
  val update3 = rankingPointsDAO.save(rankResult.map(tuple => tuple._2))

  Await.ready(update2,10 minute)
  Await.ready(update3,10 minute)

  println("All date processed!")

/*
  rankResults.onComplete {
    case Success(value) => println("The final value: "+value)
    case Failure(e) => println("Error: ");e.printStackTrace()
  }
*/
}
