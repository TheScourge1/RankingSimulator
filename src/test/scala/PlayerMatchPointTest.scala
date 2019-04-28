import java.sql.{Date, Timestamp}
import java.text.SimpleDateFormat

import org.scalatest.FunSuite
import rankingSim.dao.{RankingPointsRepo}
import rankingSim.models.PlayerMatchPoint

import scala.concurrent.Await
import scala.concurrent.duration._

class PlayerMatchPointTest extends FunSuite{

  val rankingPointDAO = new RankingPointsRepo
  var formate = new SimpleDateFormat("dd/MM/yyyy")

  test("saveRankingPoint"){
    val rankingPoint = new PlayerMatchPoint(0,"123456789","MATCH_1",32,1032,16,0.75,
      new Timestamp(formate.parse("01/02/2019").getTime()))
    val future = rankingPointDAO.save(rankingPoint)
    val rankingPointOption = Await.result(future,1.seconds)

    assert(rankingPointOption > 0)
    println("Save value: "+rankingPointOption.intValue())

    val deleteOption = rankingPointDAO.delete(rankingPointOption.intValue() )
  }

}
