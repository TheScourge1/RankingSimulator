package rankingSim.models

import java.sql.Timestamp
import java.text.SimpleDateFormat

import slick.lifted.Tag
import slick.jdbc.MySQLProfile.api._


case class RankingPoint(id: Int,
                        playerId:String,
                        matchId: String,
                        points: Int,
                        newBalance:Int,
                        kValue: Int,
                        winChange: Double,
                        calcDate: Timestamp)


  class RankingPoints(tag: Tag) extends Table[RankingPoint](tag, "ranking_points") {

    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def playerId = column[String]("player_id")

    def matchId = column[String]("match_id")

    def points = column[Int]("obtained_points")

    def newBalance = column[Int]("new_balance")

    def kValue = column[Int]("k_value")

    def winChange = column[Double]("win_chance")

    def calcDate = column[Timestamp]("calc_dat")

    def * = (id,playerId, matchId,points,newBalance,kValue,winChange,calcDate) <> ((RankingPoint.apply _).tupled, RankingPoint.unapply)
  }





