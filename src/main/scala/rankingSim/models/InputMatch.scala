package rankingSim.models

import java.sql.Timestamp
import java.text.SimpleDateFormat

import GenericSlick.{BaseEntity, BaseTable}
import slick.lifted.Tag
import slick.jdbc.MySQLProfile.api._

case class InputMatch(
                       id: Int,
                       tournamentId: Int,
                       teammatchId: Int,
                       matchDate: Timestamp,
                       description: String,
                       matchTypeid: String,
                       team1Player1MemberId: String,
                       team1Player2MemberId: String,
                       team2Player1MemberId: String,
                       team2Player2MemberId: String,
                       winner: String,
                       set1Team1: Int,
                       set1Team2: Int,
                       set2Team1: Int,
                       set2Team2: Int,
                       set3Team1: Int,
                       set3Team2: Int
) extends BaseEntity

//for reading from csv
object InputMatch{
  def fromStringSequence(sequence: Seq[String] ): InputMatch = {
    var formate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
    val result = new InputMatch(
      0,
      sequence(0).toInt,
      sequence(1).toInt,
      new Timestamp(formate.parse(sequence(2)).getTime()),
      sequence(3),
      sequence(4),
      sequence(5),
      sequence(6),
      sequence(7),
      sequence(8),
      sequence(9),
      sequence(10).toInt,
      sequence(11).toInt,
      sequence(12).toInt,
      sequence(13).toInt,
      sequence(14).toInt,
      sequence(15).toInt
    )
    result
  }
}

class InputMatchTable(tag: Tag) extends BaseTable[InputMatch](tag, None,"inputmatch") {
  def tournamentId = column[Int]("tournamentid")
  def teammatchId = column[Int]("teammatchId")
  def matchDate = column[Timestamp]("match_date")
  def description = column[String]("description")
  def matchTypeid = column[String]("matchTypeid")
  def team1Player1MemberId = column[String]("team1Player1MemberId")
  def team1Player2MemberId = column[String]("team1Player2MemberId")
  def team2Player1MemberId = column[String]("team2Player1MemberId")
  def team2Player2MemberId = column[String]("team2Player2MemberId")
  def winner = column[String]("winner")
  def set1Team1 = column[Int]("set1Team1")
  def set1Team2 = column[Int]("set1Team2")
  def set2Team1 = column[Int]("set2Team1")
  def set2Team2 = column[Int]("set2Team2")
  def set3Team1 = column[Int]("set3Team1")
  def set3Team2 = column[Int]("set3Team2")

  def * = (id,tournamentId,teammatchId,matchDate,description,matchTypeid,
    team1Player1MemberId,team1Player2MemberId,team2Player1MemberId,team2Player2MemberId,winner,set1Team1,
    set1Team2,set2Team1,set2Team2,set3Team1,set3Team2) <> ((InputMatch.apply _).tupled, InputMatch.unapply)

}
