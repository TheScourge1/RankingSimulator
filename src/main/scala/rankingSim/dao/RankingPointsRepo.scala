package rankingSim.dao

import GenericSlick.{BaseRepo}
import rankingSim.models.{PlayerMatchPoint, PlayerMatchPointTable}
import slick.lifted.TableQuery

class RankingPointsRepo extends BaseRepo[PlayerMatchPointTable,PlayerMatchPoint] (TableQuery[PlayerMatchPointTable])  {


}
