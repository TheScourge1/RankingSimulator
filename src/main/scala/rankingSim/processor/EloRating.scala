package rankingSim.processor

import rankingSim.models.{InputMatch, PlayerRank, RankingPoints}

object EloRating {

  val KValue

  def processSinglesGame(playerRanks: Seq[PlayerRank],game: InputMatch,kValue: (PlayerRank,PlayerRank) => Int): RankingPoints  ={
    val player1 = playerRanks.filter(_.playerId == game.team1Player1MemberId).head
    val player2 = playerRanks.filter(_.playerId == game.team2Player1MemberId).head

    if(player1 == null) throw new Exception("Missing player1 "+game.team1Player1MemberId + " in match: " + game.matchId)
    if(player2 == null) throw new Exception("Missing player2 "+game.team1Player2MemberId + " in match: " + game.matchId)

    val probPlayer1 = 1/(1+Math.pow(10,player2.points - player1.points/400))
    val probPlayer2 = 1 - probPlayer1

    val pointsPlayer1 = player1.points + game.result probPlayer1 * kValue(player1,player2)

    return new RankingPoints()
  }

}
