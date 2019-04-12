package rankingSim.models

import java.sql.Date
import java.text.SimpleDateFormat

import slick.lifted.Tag
import slick.jdbc.MySQLProfile.api._


case class PlayerRank(playerId: Int,
                       points: Int,
                       last_dat: Date)

/*
insert into rank_saldo (player_id,points, last_dat)
select player_id,case when single_rank = 'A' then 2800
when single_rank = 'B1' then 2400
when single_rank = 'B2' then 2000
when single_rank = 'C1' then 1600
when single_rank = 'C2' then 1200
else 800 end,date
from PlayerRanking
*/

class PlayerRanks(tag: Tag) extends Table[PlayerRank](tag, "rank_saldo") {

    def playerId = column[Int]("player_id", O.PrimaryKey)

    def points = column[Int]("points")

    def rankDate = column[Date]("last_dat")

    def * = (playerId, points,rankDate) <> ((PlayerRank.apply _).tupled, PlayerRank.unapply)
  }





