import java.sql.Timestamp
import java.text.SimpleDateFormat

import rankingSim.dao.InputMatchRepo
import org.scalatest.FunSuite
import rankingSim.models.InputMatch

import scala.concurrent.Await
import scala.concurrent.duration._

class InputMatchTest extends FunSuite{

  val inputMatchDAO = new InputMatchRepo
  var formate = new SimpleDateFormat("dd/MM/yyyy")

  test("getInputMatch"){

    val inputMatch = new InputMatch(0,13,123,new Timestamp(formate.parse("01/02/2019").getTime()),"testing input","HE","123",
      "234","345","456","1",21,0,21,0,0,0)

    var future = inputMatchDAO.save(inputMatch)
    val inputMatchOption = Await.result(future,1.seconds)

    assert(inputMatchOption.intValue() > 0)

    println("Found InputMatch: "+inputMatchOption.intValue())

    future = inputMatchDAO.delete(inputMatchOption.intValue())
    Await.result(future,1.seconds)

  }



}
