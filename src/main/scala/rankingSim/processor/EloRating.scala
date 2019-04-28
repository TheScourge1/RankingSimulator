package rankingSim.processor

import java.sql.{Date, Timestamp}

import rankingSim.models.{InputMatch, PlayerRank, PlayerMatchPoint}

import scala.collection.mutable

object EloRating {

  def NEWPLAYER_RATING_D = 800

  def processSinglesGame(playerRanks: Map[String,PlayerRank],game: InputMatch,kValue: (PlayerRank,PlayerRank) => Int): (PlayerMatchPoint,PlayerMatchPoint) = {
    val player1 = playerRanks.getOrElse(game.team1Player1MemberId,
      throw new Exception("Missing player1 "+game.team1Player1MemberId + " in match: " + game.description))
    val player2 = playerRanks.getOrElse(game.team2Player1MemberId,
      throw new Exception("Missing player2 "+game.team2Player1MemberId + " in match: " + game.description))

    val probPlayer1 =  Math.round(100.0/(1+Math.pow(10,(player2.points - player1.points)/400.0)))/100.0
    val probPlayer2 = 1 - probPlayer1

    val p1Result = game.winner match {
      case "1" => 1
      case "2" => 0
      case _ => throw new Exception("Unknown winner found: "+game.winner)
    }

    val pointsPlayer1 = Math.round((p1Result - probPlayer1) * kValue(player1,player2)).toInt
    val pointsPlayer2 = Math.round((1-p1Result - probPlayer2) * kValue(player1,player2)).toInt

    val rP1 = new PlayerMatchPoint(0,player1.playerId,game.id.toString,pointsPlayer1,player1.points+pointsPlayer1,kValue(player1,player2),probPlayer1,game.matchDate)
    val rP2 = new PlayerMatchPoint(0,player2.playerId,game.id.toString,pointsPlayer2,player2.points+pointsPlayer2,kValue(player1,player2),probPlayer2,game.matchDate)

    (rP1,rP2)
  }

  def createMissingPlayers(playerRanks: Seq[PlayerRank],matches :Seq[InputMatch]) : Seq[PlayerRank] = {
    val playerSet = mutable.HashSet.empty[String]
    val newPlayers = mutable.HashSet.empty[String]
    playerSet++=playerRanks.map(p =>p.playerId)


    matches.foreach(game => {
      if(!playerSet.contains(game.team1Player1MemberId) && !newPlayers.contains(game.team1Player1MemberId))
        newPlayers+=game.team1Player1MemberId
      if(!playerSet.contains(game.team2Player1MemberId)  && !newPlayers.contains(game.team2Player1MemberId))
        newPlayers+=game.team2Player1MemberId}
    )

    newPlayers.map(playerId => new PlayerRank(0,playerId,NEWPLAYER_RATING_D,new Timestamp(new java.util.Date().getTime))).toSeq
  }
}
