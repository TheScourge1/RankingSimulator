import rankingSim.dao.PlayersDAO
import org.scalatest.FunSuite

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class PlayerTest extends FunSuite{

  val playerDAO = PlayersDAO

  test("getPlayer"){
    val future = playerDAO.getAll
    val playerOption = Await.result(future,1.seconds)

    assert(playerOption.headOption.get.name != null)

    println("Found player: "+playerOption.headOption.get.name)
  }

}
