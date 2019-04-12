package rankingSim.models

import slick.lifted.Tag
import slick.jdbc.MySQLProfile.api._

case class InputMatch(
        tournamentId: Int,
        teammatchId: Int,
        matchOrder: Int,
        matchId: Int,
        matchTypeid: String,
        matchTypeno: Int,
        team1Player1MemberId: String,
        team1Player2MemberId: String,
        team2Player1MemberId: String,
        team2Player2MemberId: String,
        set1Team1: Int,
        set1Team2: Int,
        set2Team1: Int,
        set2Team2: Int,
        set3Team1: Int,
        set3Team2: Int,
        team1sets: Int,
        team2sets: Int,
        result: String
)

//for reading from csv
object InputMatch{
  def fromStringSequence(sequence: Seq[String] ): InputMatch = {
    val result = new InputMatch(
      sequence(0).toInt,
      sequence(1).toInt,
      sequence(2).toInt,
      sequence(3).toInt,
      sequence(4),
      sequence(5).toInt,
      sequence(6),
      sequence(7),
      sequence(8),
      sequence(9),
      sequence(10).toInt,
      sequence(11).toInt,
      sequence(12).toInt,
      sequence(13).toInt,
      sequence(14).toInt,
      sequence(15).toInt,
      sequence(16).toInt,
      sequence(17).toInt,
      sequence(18)
    )
    result
  }
}

class InputMatches(tag: Tag) extends Table[InputMatch](tag, "inputmatch") {

  def tournamentId = column[Int]("tournamentid")
  def teammatchId = column[Int]("teammatchId")
  def matchOrder = column[Int]("matchOrder")
  def matchId = column[Int]("matchId")
  def matchTypeid = column[String]("matchTypeid")
  def matchTypeno = column[Int]("matchTypeno")
  def team1Player1MemberId = column[String]("team1Player1MemberId")
  def team1Player2MemberId = column[String]("team1Player2MemberId")
  def team2Player1MemberId = column[String]("team2Player1MemberId")
  def team2Player2MemberId = column[String]("team2Player2MemberId")
  def set1Team1 = column[Int]("set1Team1")
  def set1Team2 = column[Int]("set1Team2")
  def set2Team1 = column[Int]("set2Team1")
  def set2Team2 = column[Int]("set2Team2")
  def set3Team1 = column[Int]("set3Team1")
  def set3Team2 = column[Int]("set3Team2")
  def team1sets = column[Int]("team1sets")
  def team2sets = column[Int]("team2sets")
  def result = column[String]("result")

  def * = (tournamentId,teammatchId,matchOrder,matchId,matchTypeid,matchTypeno,
    team1Player1MemberId,team1Player2MemberId,team2Player1MemberId,team2Player2MemberId,set1Team1,
    set1Team2,set2Team1,set2Team2,set3Team1,set3Team2,team1sets,team2sets,result) <> ((InputMatch.apply _).tupled, InputMatch.unapply)

}
