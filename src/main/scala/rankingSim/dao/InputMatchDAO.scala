package rankingSim.dao

import rankingSim.models.{InputMatch, InputMatches}

import scala.concurrent.Future

object InputMatchDAO extends MySQLDB   {
  import profile.api._

  val table = TableQuery[InputMatches]

  def getAll :Future[Seq[InputMatch]] =  {

    table.result.statements.foreach(println)
    db.run(table.result)
  }

  def save(inputMatch: InputMatch): Future[Int] ={
    val insertVal = {table returning table.map(_.matchId) +=inputMatch}
    db.run(insertVal)
  }

  def save(inputMatches: Seq[InputMatch]): Future[Option[Int]] ={
    val insertBatch: DBIO[Option[Int]] = table ++=inputMatches
    db.run(insertBatch)
  }
}
