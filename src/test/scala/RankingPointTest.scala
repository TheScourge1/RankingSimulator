import java.sql.Date
import java.text.SimpleDateFormat

import org.scalatest.FunSuite
import rankingSim.dao.{PlayersDAO, RankingPointsDAO}
import rankingSim.models.RankingPoint

import scala.concurrent.Await
import scala.concurrent.duration._

class RankingPointTest extends FunSuite{

  val rankingPointDAO = RankingPointsDAO
  var formate = new SimpleDateFormat("dd/MM/yyyy")

  test("saveRankingPoint"){
    val rankingPoint = new RankingPoint(0,123456789,"MATCH_1",32,1032,16,0.75,
      new Date(formate.parse("01/02/2019").getTime()))
    val future = rankingPointDAO.save(rankingPoint)
    val rankingPointOption = Await.result(future,1.seconds)

    assert(rankingPointOption.intValue() != null)
    println("Save value: "+rankingPointOption.intValue())

    val deleteOption = rankingPointDAO.delete(rankingPointOption.intValue() )
  }

}
