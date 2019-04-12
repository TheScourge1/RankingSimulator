import org.scalatest.FunSuite
import rankingSim.dao.{PlayerRankDAO}

import scala.concurrent.Await
import scala.concurrent.duration._

class RankBalanceTest extends FunSuite{

  val rankBalanceDAO = PlayerRankDAO

  test("getPlayer"){
    val future = rankBalanceDAO.getAll
    val playerOption = Await.result(future,1.seconds)

    assert(playerOption.headOption.get.playerId != null)

    println("Balance found: "+playerOption.headOption.get.playerId + " - " + playerOption.headOption.get.points)
  }

}
