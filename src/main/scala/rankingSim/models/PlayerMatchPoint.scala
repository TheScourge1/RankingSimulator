package rankingSim.models

import java.sql.Timestamp
import java.text.SimpleDateFormat

import GenericSlick.{BaseEntity, BaseTable}
import slick.lifted.Tag
import slick.jdbc.MySQLProfile.api._


case class PlayerMatchPoint(id: Int,
                            playerId:String,
                            matchId: String,
                            points: Int,
                            newBalance:Int,
                            kValue: Int,
                            winChange: Double,
                            calcDate: Timestamp) extends BaseEntity


  class PlayerMatchPointTable(tag: Tag) extends BaseTable[PlayerMatchPoint](tag, None,"player_matchpoint") {

    def playerId = column[String]("player_id")

    def matchId = column[String]("match_id")

    def points = column[Int]("obtained_points")

    def newBalance = column[Int]("new_balance")

    def kValue = column[Int]("k_value")

    def winChange = column[Double]("win_chance")

    def calcDate = column[Timestamp]("calc_dat")

    def * = (id,playerId, matchId,points,newBalance,kValue,winChange,calcDate) <> ((PlayerMatchPoint.apply _).tupled, PlayerMatchPoint.unapply)
  }





