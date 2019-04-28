package rankingSim.models

import java.sql.Date
import java.text.SimpleDateFormat

import GenericSlick.{BaseEntity, BaseTable}
import slick.lifted.Tag
import slick.jdbc.MySQLProfile.api._


  case class Player (id: Int,
                    playerId: String,
                    name: String,
                    rankDate: Date,
                    rank: String) extends BaseEntity


//for reading from csv
object Player{
  def fromStringSequence(sequence: Seq[String] ): Player = {
    var formate = new SimpleDateFormat("dd/MM/yyyy")
    val result = new Player(
      0,
      sequence(1),
      sequence(0),
      new Date(formate.parse(sequence(2)).getTime()),
      sequence(3)
    )
    result
  }
}


  class PlayerTable(tag: Tag) extends BaseTable[Player](tag, None,"Player") {

   // def id = column[Int]("id", O.PrimaryKey,O.AutoInc)

    def playerId = column[String]("player_id")

    def name = column[String]("name")

    def rankDate = column[Date]("date")

    def rank = column[String]("single_rank")

    def * = (id, playerId, name,rankDate,  rank) <> ((Player.apply _).tupled, Player.unapply)
  }





