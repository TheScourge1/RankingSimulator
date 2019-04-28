package rankingSim.models

import java.sql.Timestamp

import GenericSlick.{BaseEntity, BaseTable}
import slick.lifted.Tag
import slick.jdbc.MySQLProfile.api._


case class PlayerRank( id: Int,
                       playerId: String,
                       points: Int,
                       last_dat: Timestamp) extends BaseEntity

/*
insert into player_rank_balance (player_id,points, last_dat)
select player_id,case when single_rank = 'A' then 2800
when single_rank = 'B1' then 2400
when single_rank = 'B2' then 2000
when single_rank = 'C1' then 1600
when single_rank = 'C2' then 1200
else 800 end,date
from PlayerRanking
*/

class PlayerRankBalanceTable(tag: Tag) extends BaseTable[PlayerRank](tag, None,"player_rank_balance") {

    def playerId = column[String]("player_id", O.Unique)

    def points = column[Int]("points")

    def rankDate = column[Timestamp]("last_dat")

    def * = (id,playerId, points,rankDate) <> ((PlayerRank.apply _).tupled, PlayerRank.unapply)
  }





