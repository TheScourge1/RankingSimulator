package rankingSim.dao

import GenericSlick.{BaseRepo}
import rankingSim.models.{PlayerRank, PlayerRankBalanceTable}
import slick.lifted.TableQuery

class PlayerRankRepo extends BaseRepo[PlayerRankBalanceTable,PlayerRank](TableQuery[PlayerRankBalanceTable])   {

}
