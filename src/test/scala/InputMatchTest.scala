import rankingSim.dao.InputMatchDAO
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

class InputMatchTest extends FunSuite{

  val inputMatchDAO = InputMatchDAO

  test("getInputMatch"){
    val future = inputMatchDAO.getAll
    val inputMatchOption = Await.result(future,1.seconds)

    assert(inputMatchOption.headOption.get != null)

    println("Found InputMatch: "+inputMatchOption.head)
  }



}
