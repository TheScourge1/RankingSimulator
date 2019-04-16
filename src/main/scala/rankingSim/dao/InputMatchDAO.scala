package rankingSim.dao

import rankingSim.models.{InputMatch, InputMatches}

import scala.concurrent.Future

object InputMatchDAO extends MySQLDB   {
  import profile.api._

  val table = TableQuery[InputMatches]

  def getAll :Future[Seq[InputMatch]] =  {

    db.run(table.result)
  }

  def getAllHE :Future[Seq[InputMatch]] =  {
    val query = table.filter(game => game.matchTypeid === "HE")
    db.run(query.result)
  }

  def save(inputMatch: InputMatch): Future[Int] ={
    val insertVal = {table returning table.map(_.id) +=inputMatch}
    db.run(insertVal)
  }

  def save(inputMatches: Seq[InputMatch]): Future[Option[Int]] ={
    val insertBatch: DBIO[Option[Int]] = table ++=inputMatches
    db.run(insertBatch)
  }

  def delete(id: Int): Future[Int] = {
    val q = table.filter(_.id === id)
    val action = q.delete
    db.run(action)
  }
}
